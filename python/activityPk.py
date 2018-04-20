# -*- coding:utf-8 -*-
import datetime
from MySqlUtil import MySql
import xlwt
import xlrd

'''活动相关'''

# docker
activityDB = MySql(
    {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'live_operating_activities'})

userDB = MySql(
    {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'lizhi_app'})

# 正式环境
# activityDB = MySql(
#     {'host': 'live-operating-act-3353-mydb-ser.service.dbconsul', 'port': 3353, 'user': 'operating_act_w',
#      'passwd': 'BAdze2KSwhi6tB68',
#      'dbName': 'live_operating_activities'})
#
# userDB = MySql(
#     {'host': 'r_app_db_server.lzfm.com', 'port': 3306, 'user': 'lizhi_admin', 'passwd': 'lizhi_app!ee12',
#      'dbName': 'lizhi_app'})

#新增参赛活动名单
def add_start_list(userid,activityid):
    sql = """
        insert into start_list(user_id,user_no,activity_id,group_id,status,modify_time,create_time) 
        values(%s,0,%s,0,0,now(),now());
    """
    activityDB.insert(sql % (userid,activityid),[])

def get_user_by_band(band):
    sql = """
        SELECT u.* FROM USER u JOIN radio_nj nj 
        ON u.id = nj.user_id
        JOIN radio r ON nj.`radio_id`=r.id AND r.band='%s';
    """
    userDB.query(sql % str(band))
    return userDB.fetchOneRow()

def read_excle(filename, sheetnmu, col):
    workbook = xlrd.open_workbook(filename)
    sheet = workbook.sheet_by_index(sheetnmu)
    return sheet.col_values(col, start_rowx=1)

#新增参赛活动名单
def add_activity_list():
    for band in read_excle("test.xlsx",0,0):
        user = get_user_by_band(int(band))
        if not user:
            print("错误波段号", int(band))
            continue
        print(user["id"])
        add_start_list(user["id"],2)

def insert_print_sql():
    row_list=[2630689278920757292,2630689790021866028,2634935268557193260,2634936179090260524,2634936550604932140,
              2634937854127506988,2634937525562507308,2634941601486474796,2634938208462307884,2634938601451817004,
              2634941320166116396,2634942385318007340]
    # row_list=[2630689278920757292,2630689790021866028,2634935268557193260,2634936179090260524,2634936550604932140,
    #           2634937854127506988,2634937525562507308,2634941601486474796,2634938208462307884,2634938601451817004,
    #           2634941320166116396,2634942385318007340]
    for user_id in row_list:
        sql = """
                insert into start_list(user_id,user_no,activity_id,group_id,status,modify_time,create_time) 
                values(%s,0,%s,0,0,now(),now());
          """
        print(sql%(user_id,2))

if __name__=="__main__":
    print("开始:", datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    try:
        # add_activity_list()
        insert_print_sql()
    except Exception as e:
        print(e)
    finally:
        userDB.close()
        activityDB.close()
        print("结束:", datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

