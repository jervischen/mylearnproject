# -*- coding:utf-8 -*-
import redis
import datetime
import pymysql

host = "172.17.6.232"
port = 3306
user = "fmuser"
password = "fmpass"

redis_host = '172.17.6.232'
redis_port = '6379'


def getMySQLConnection(host, port, user, password, database="lizhi_app", charset='utf8'):
    """
    建立数据库连接
    """
    return pymysql.connect(host=host, port=port, user=user, password=password, database=database, charset=charset,
                           cursorclass=pymysql.cursors.DictCursor)


# 查询数据
connection = getMySQLConnection(host, port, user, password)
cursor_ex = connection.cursor()
sql_band = "SELECT radio.* FROM radio_nj JOIN radio ON radio_nj.radio_id=radio.id AND radio_nj.user_id=%d"
sql_user = "SELECT * FROM USER WHERE id=%d"


def getYesterday():
    today = datetime.date.today()
    oneday = datetime.timedelta(days=1)
    yesterday = today - oneday
    return yesterday


# 获取昨天日期
#date_yest = getYesterday().strftime("%Y%m%d")
date_yest = '20180314'
print(date_yest)
# 主播排行key
sort_key = 'LZ_LIVE_ACTIVITY_RANK_SORTED_SET_PEACH_'
# 头号粉丝排行榜
fan_key = 'LZ_LIVE_ACTIVITY_FAN_SORTED_SET_PEACH_'
# redis连接
r = redis.Redis(host=redis_host, port=redis_port)


mission_level = ['_3', '_2', '_1']
for level in mission_level:
    if level == '_3':
        print("桃花之神")
    elif level == '_2':
        print("桃花之仙")
    else:
        print("桃花之妖怪")

    # 主播
    nj_list = r.zrevrangebyscore(sort_key + '20180314' + level, "+inf", "-inf", 0, 3,withscores=True)
    print(type(nj_list))
    print(type(nj_list[0]))
    break;
    for i in range(len(nj_list)):
        print("主播id==", str(nj_list[i]))
        cursor_ex.execute(sql_band % int(nj_list[i]))
        row_band = cursor_ex.fetchone()
        cursor_ex.execute(sql_user % int(nj_list[i]))
        row_user = cursor_ex.fetchone()
        print("第" + str(i + 1) + "名主播:" + row_band['band'] + "   " + row_user['name'] + "  ")

        fan_list = r.zrevrangebyscore(fan_key + bytes.decode(nj_list[i]), "+inf", "-inf", 0, 3)
        for fan_id in fan_list:
            cursor_ex.execute(sql_band % int(fan_id))
            row_band = cursor_ex.fetchone()
            cursor_ex.execute(sql_user % int(fan_id))
            row_user = cursor_ex.fetchone()
            print("粉丝:" + row_band['band'] + "   " + row_user['name'] + "  " + bytes.decode(fan_id))
        print()

# 提交，不然无法保存新建或者修改的数据
connection.commit()
# 关闭游标
cursor_ex.close()
# 关闭连接
connection.close()
