# -*- coding:utf-8 -*-
import redis
import datetime
import pymysql
import xlwt
import time

# 用户信息数据库
uhost = "r_app_db_server.lzfm.com"
uport = 3306
udatabase = "lizhi_app"
uuser = "lizhi_admin"
upassword = "lizhi_app!ee12"

# 礼物表，查询荔枝数
ghost = "r_live_gift_db_server.lzfm.com"
gport = 3341
gdatabase = "lizhi_live_gift"
guser = "livegift_r"
gpassword = "liveR0912GIFT"

redis_host = '192.168.1.20'
redis_port = '6021'

# docker环境
uhost = "172.17.6.232"
uport = 3306
udatabase = "lizhi_app"
uuser = "fmuser"
upassword = "fmpass"

ghost = "172.17.6.232"
gport = 3306
gdatabase = "lizhi_live_gift"
guser = "fmuser"
gpassword = "fmpass"

redis_host = '172.17.6.232'
redis_port = '6379'


def getMySQLConnection(host, port, user, password, database, charset='utf8'):
    """
    建立数据库连接
    """
    return pymysql.connect(host=host, port=port, user=user, password=password, database=database, charset=charset,
                           cursorclass=pymysql.cursors.DictCursor)


# 用户查询数据
userDB = getMySQLConnection(uhost, uport, uuser, upassword, udatabase)
userCur = userDB.cursor()
sql_band = "SELECT radio.* FROM radio_nj JOIN radio ON radio_nj.radio_id=radio.id AND radio_nj.user_id=%d"
sql_user = "SELECT * FROM USER WHERE id=%d"

# 荔枝数
litchiDB = getMySQLConnection(ghost, gport, guser, gpassword, gdatabase)
litchiCur = litchiDB.cursor()
sql_litchi1 = "SELECT sum(total_litchi_amount) num FROM live_give_gift_action where nj_id=%d and create_time BETWEEN '2018-03-09 00:00:00' and '2018-03-13 23:59:59'"
sql_litchi2 = "SELECT sum(total_litchi_amount) num FROM live_give_gift_action where nj_id=%d and create_time BETWEEN '2018-03-15 00:00:00' and '2018-03-19 23:59:59'"

# redis连接
# 主播排行key
sort_key = 'LZ_LIVE_ACTIVITY_RANK_SORTED_SET_PEACH_'
# 头号粉丝排行榜
fan_key = 'LZ_LIVE_ACTIVITY_FAN_SORTED_SET_PEACH_'
# redis连接
r = redis.Redis(host=redis_host, port=redis_port)


def getYesterday():
    today = datetime.date.today()
    oneday = datetime.timedelta(days=1)
    yesterday = today - oneday
    return yesterday


# 创建一个Workbook对象，这就相当于创建了一个Excel文件
book = xlwt.Workbook(encoding='utf-8', style_compression=0)


# 获取主播排行榜
def get_rank_data(level):
    if level == '_3':
        sheet = book.add_sheet("桃花之神", cell_overwrite_ok=True)
    elif level == '_2':
        sheet = book.add_sheet("桃花之仙", cell_overwrite_ok=True)
    else:
        sheet = book.add_sheet("桃花之妖", cell_overwrite_ok=True)

    sheet.write(0, 0, '波段号')
    sheet.write(0, 1, '主播号')
    sheet.write(0, 2, '式神名')
    sheet.write(0, 3, '式神数')
    sheet.write(0, 4, '完成任务数')
    sheet.write(0, 5, '3月9~13荔枝数')
    sheet.write(0, 6, '3月15~19荔枝数')

    nj_list = r.zrevrangebyscore(sort_key + '20180315' + level, "+inf", "-inf", withscores=True)
    nj_list1 = r.zrevrangebyscore(sort_key + '20180316' + level, "+inf", "-inf", withscores=True)
    nj_list2 = r.zrevrangebyscore(sort_key + '20180317' + level, "+inf", "-inf", withscores=True)
    nj_list3 = r.zrevrangebyscore(sort_key + '20180318' + level, "+inf", "-inf", withscores=True)
    nj_list4 = r.zrevrangebyscore(sort_key + '20180319' + level, "+inf", "-inf", withscores=True)

    list_all = nj_list + nj_list1 + nj_list2 + nj_list3 + nj_list4
    # 转化成字典(nj_dict)
    nj_dict = {}
    for i in range(len(list_all)):
        key = bytes.decode(list_all[i][0])
        if key not in nj_dict:
            nj_dict[key] = list_all[i][1]
        else:
            nj_dict[key] = nj_dict[key] + list_all[i][1]
    count = 0
    for key in nj_dict.keys():
        userCur.execute(sql_band % int(key))
        row_band = userCur.fetchone()
        userCur.execute(sql_user % int(key))
        row_user = userCur.fetchone()
        userDB.commit()
        # 荔枝数
        litchiCur.execute(sql_litchi1 % int(key))
        litchi1 = litchiCur.fetchone()
        litchiCur.execute(sql_litchi2 % int(key))
        litchi2 = litchiCur.fetchone()
        litchiDB.commit()

        index = count + 1
        count += 1
        sheet.write(index, 0, row_band['band'])  # 波段号
        sheet.write(index, 1, row_user['name'])  # 主播号
        if level == '_3':
            sheet.write(index, 2, "桃花之神")
        elif level == '_2':
            sheet.write(index, 2, "桃花之仙")
        else:
            sheet.write(index, 2, "桃花之妖")
        sheet.write(index, 3, nj_dict[key])  # 式神数
        sheet.write(index, 4, nj_dict[key] * 3)  # 完成任务数
        sheet.write(index, 5, litchi1['num'])
        sheet.write(index, 6, litchi2['num'])


def closeDB():
    # 关闭游标
    userCur.close()
    # 关闭连接
    userDB.close()
    litchiCur.close()
    litchiDB.close()


# 执行
if __name__ == "__main__":
    try:
        # 开始时间
        begin_time = int(time.time())
        print("begin time:%d" % begin_time)

        mission_level = ['_3', '_2', '_1']
        for level in mission_level:
            get_rank_data(level)
        book.save(r'rank.xls')

        # 结束时间
        end_time = int(time.time())
        print("end time:%d" % end_time)
        print("total time:%d" % (end_time - begin_time))
    except Exception as e:
        print(e)
    finally:
        closeDB()
