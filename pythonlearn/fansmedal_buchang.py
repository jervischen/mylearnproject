# coding=utf-8
import time
import datetime
from MySqlUtil import MySql
import redis

# docker
# fansDB = MySql(
#     {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser',
#      'passwd': 'fmpass', 'dbName': 'live_fans_level'})


fansDB = MySql(
    {'host': 'live_fans_level_3323_mydb_ser.lzfm.com', 'port': 3323, 'user': 'fans_level_w', 'passwd': 'ayk8N1nKMa',
     'dbName': 'live_fans_level'})

# r = redis.Redis(host="redis.ops.lizhi.fm", port=6379)
r = redis.Redis(host="live_fans_level_6064_rds_ser.lzfm.com", port=6064)


def get_top_fansLevel():
    # 有些过期数据
    sql = """
        SELECT user_id,nj_id,MAX(medal_level) level FROM fans_medal_obtain_history 
        WHERE create_time BETWEEN '2018-05-10 00:00:00' AND '2018-06-07 23:59:59' and medal_level>0  GROUP BY user_id,nj_id
    """
    fansDB.query(sql)
    return fansDB.fetchAllRows()


def get_top_exp(user_id, nj_id):
    sql = """
         SELECT user_id,nj_id,MAX(current_exp) exp FROM fans_exp_history WHERE user_id=%d AND nj_id=%d 
         AND create_time BETWEEN '2018-06-05 00:00:00' AND '2018-06-07 23:59:59'
    """
    fansDB.query(sql % (int(user_id), int(nj_id)))
    return fansDB.fetchOneRow()


def get_max_exp():
    sql = """
        SELECT user_id,nj_id,increase_exp,MAX(current_exp) current_exp,coins FROM fans_exp_history WHERE 
        increase_exp > 0 AND
        create_time BETWEEN '2018-06-06 00:00:00' AND '2018-06-07 23:59:59' GROUP BY user_id,nj_id
    """
    fansDB.query(sql)
    return fansDB.fetchAllRows()


def insert_redis_fans_exp(user_id, nj_id, level, exp):
    # 插入等级
    r_key = "LZ_FANSLEVEL_USER_FANS_MEDAL_%d" % user_id
    name = str(nj_id)
    level_json = "{\"level\":%d,\"time\":%d}" % (int(level), int(out_date()) * 1000)
    r.hset(r_key, name, level_json)

    # # 生成主播勋章等级图片
    # l_key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_LEVEL_%d" % nj_id
    # r.hset(l_key, level, int(time.time()) * 1000)
    #
    # # 插入经验，粉丝经验排行榜(Sorted Set)，key为key_jockeyId,field为userId，score为相应的经验值
    # ranking_key = "LZ_FANSLEVEL_RANKING_%d" % nj_id
    # r.zadd(ranking_key, user_id, exp)
    #
    # # 全站粉丝排行(Sorted Set),key为key,field为userId_jockeyId, score为相应的经验值
    # station_rank_key = "LZ_FANSLEVEL_STATION_RANK"
    # me = "%d_%d" % (user_id, nj_id)
    # r.zadd(station_rank_key, me, exp)


# 补偿所有等级
def bu_chang_level_and_exp():
    fans_list = get_top_fansLevel()
    for fans in fans_list:
        user_id = fans["user_id"]
        nj_id = fans["nj_id"]
        level = fans["level"]
        # topExp = get_top_exp(user_id, nj_id)
        # insert_redis_fans_exp(user_id, nj_id, level, topExp["exp"])
        insert_redis_fans_exp(user_id, nj_id, level, 10)


def redis_max_exp(user_id, nj_id, exp):
    # 插入经验，粉丝经验排行榜(Sorted Set)，key为key_jockeyId,field为userId，score为相应的经验值
    ranking_key = "LZ_FANSLEVEL_RANKING_%d" % int(nj_id)
    r.zadd(ranking_key, user_id, exp)

    # 全站粉丝排行(Sorted Set),key为key,field为userId_jockeyId, score为相应的经验值
    station_rank_key = "LZ_FANSLEVEL_STATION_RANK"
    me = "%d_%d" % (user_id, nj_id)
    r.zadd(station_rank_key, me, exp)


# 补偿所有用户经验
def max_exp_compensation():
    max_exp = get_max_exp()
    for m_exp in max_exp:
        user_id = m_exp["user_id"]
        nj_id = m_exp["nj_id"]
        exp = int(m_exp["current_exp"] + m_exp["increase_exp"] * m_exp["increase_exp"] / (m_exp["coins"] * 10))
        print(m_exp, exp)
        redis_max_exp(user_id, nj_id, exp)


def add_buffer(user_id, nj_id):
    continue_day = "LZ_FANSLEVEL_SENDGIFTS_TIMES_%d_20180607" % user_id
    continue_day1 = "LZ_FANSLEVEL_SENDGIFTS_TIMES_%d_20180605" % user_id
    continue_day2 = "LZ_FANSLEVEL_SENDGIFTS_TIMES_%d_20180606" % user_id
    day = r.hget(continue_day1,nj_id)
    day1 = r.hget(continue_day1,nj_id)
    day2 = r.hget(continue_day2,nj_id)

    if day is None:
        day = 0
    if day1 is None:
        day1 = 0
    if day2 is None:
        day2 = 0
    print(user_id,nj_id,int(day),int(day1),int(day2))
    max = max_num(int(day),int(day1),int(day2))
    if max != 0:
        r.hset(continue_day,nj_id,max)

def max_num(one,two,three):
    if one > two:
        if one > three:
            return one
        else:
            return three
    else:
        if two > three:
            return two
        else:
            return three

#补偿buffer
def buffer_compensation():
    max_exp = get_max_exp()
    for m_exp in max_exp:
        user_id = m_exp["user_id"]
        nj_id = m_exp["nj_id"]
        add_buffer(user_id, nj_id)

def out_date():
    today = datetime.date.today()
    oneday = datetime.timedelta(days=7)
    yesterday = today + oneday
    t = yesterday.timetuple()
    return time.mktime(t)


# 执行
if __name__ == "__main__":
    try:
        # 开始时间
        begin_time = int(time.time())
        print("begin time:%d" % begin_time)
        buffer_compensation()
        # add_buffer(2619871271973103660,2591207010196441132)
        # 结束时间
        end_time = int(time.time())
        print("end time:%d" % end_time)
        print("total time:%d" % (end_time - begin_time))
    except Exception as e:
        print(e)
    finally:
        fansDB.close()
