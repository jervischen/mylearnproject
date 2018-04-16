# -*- coding:utf-8 -*-
import datetime
from MySqlUtil import MySql
import xlwt
import xlrd

'''导出主播新增粉丝勋章数量'''

# docker
# fansDB = MySql(
#     {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'live_fans_level'})
#
# userDB = MySql(
#     {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'lizhi_app'})


# 正式环境
fansDB = MySql(
    {'host': 'live_fans_level_3323_mydb_ser.lzfm.com', 'port': 3323, 'user': 'fans_level_w', 'passwd': 'ayk8N1nKMa',
     'dbName': 'live_fans_level'})

userDB = MySql(
    {'host': 'r_app_db_server.lzfm.com', 'port': 3306, 'user': 'lizhi_admin', 'passwd': 'lizhi_app!ee12',
     'dbName': 'lizhi_app'})


def get_medal_new_user(start, end):
    ''''取出日期前已经获得勋章的人，右连接在规定日期获得勋章的人取已获得勋章为null,则为新增用户'''
    select_sql = """
            select a.nj_id,sum(num) num from 
           (SELECT new_medal.nj_id,COUNT(new_medal.user_id) num FROM
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time < '%s'
        GROUP BY user_id,nj_id HAVING SUM(increase_exp) > 0) have_medal
        RIGHT JOIN
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time BETWEEN '%s' AND '%s' 
        GROUP BY user_id,nj_id HAVING SUM(increase_exp) > 0) new_medal
        ON have_medal.nj_id = new_medal.nj_id AND have_medal.user_id = new_medal.user_id
        WHERE have_medal.user_id IS NULL
        GROUP BY new_medal.user_id) a
     """
    fansDB.query(select_sql % (start, start, end))
    return fansDB.fetchAllRows()


def get_medal_new_user_by_njId(start, end, njId):
    ''''取出日期前已经获得勋章的人，右连接在规定日期获得勋章的人取已获得勋章为null,则为新增用户'''
    select_sql = """
        SELECT new_medal.nj_id,COUNT(new_medal.user_id) num FROM
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time < '%s'
        GROUP BY nj_id,user_id HAVING SUM(increase_exp) > 0) have_medal
        RIGHT JOIN
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time BETWEEN '%s' AND '%s' AND nj_id=%d
        GROUP BY nj_id,user_id HAVING SUM(increase_exp) > 0) new_medal
        ON have_medal.nj_id = new_medal.nj_id AND have_medal.user_id = new_medal.user_id
        WHERE have_medal.user_id IS NULL
        GROUP BY new_medal.nj_id
     """
    fansDB.query(select_sql % (start, start, end, int(njId)))
    return fansDB.fetchAllRows()


def get_username(user_id):
    sql_user = "SELECT * FROM USER WHERE id=%d"
    userDB.query(sql_user % int(user_id))
    return userDB.fetchOneRow()


def get_band(user_id):
    sql_band = "SELECT radio.* FROM radio_nj JOIN radio ON radio_nj.radio_id=radio.id AND radio_nj.user_id=%d"
    userDB.query(sql_band % int(user_id))
    return userDB.fetchOneRow()


def get_user_by_band(band):
    sql = """
        SELECT u.* FROM USER u JOIN radio_nj nj 
        ON u.id = nj.user_id
        JOIN radio r ON nj.`radio_id`=r.id AND r.band='%s';
    """
    userDB.query(sql % str(band))
    return userDB.fetchOneRow()


def get_new_fansmedal(start1, end1, start2, end2, njId):
    sql = """
        SELECT new_medal.nj_id,COUNT(new_medal.user_id) num FROM
        (SELECT nj_id,user_id FROM fans_medal_obtain_history 
        WHERE  create_time BETWEEN '%s' AND '%s' 
        GROUP BY nj_id,user_id ) have_medal
        RIGHT JOIN
        (SELECT nj_id,user_id FROM fans_medal_obtain_history 
        WHERE create_time BETWEEN '%s' AND '%s' AND nj_id=%d
        GROUP BY nj_id,user_id ) new_medal
        ON have_medal.nj_id = new_medal.nj_id AND have_medal.user_id = new_medal.user_id
        WHERE have_medal.user_id IS NULL
        GROUP BY new_medal.nj_id
    """
    fansDB.query(sql % (start1, end1, start2, end2, int(njId)))
    return fansDB.fetchOneRow()


def get_top3_exp(start, end, njId):
    sql = """
        (SELECT nj_id,user_id,SUM(increase_exp) ex FROM fans_exp_history 
        WHERE create_time BETWEEN '%s' AND '%s'
        AND nj_id=%d
        GROUP BY nj_id,user_id)e 
        ORDER BY e.ex DESC LIMIT 3
    """
    fansDB.query(sql % (start, end, int(njId)))
    return fansDB.fetchAllRows()


def getYesterday(num):
    today = datetime.date.today()
    oneday = datetime.timedelta(days=num)
    yesterday = today - oneday
    return yesterday


def save_excel(file_name, head_sheet_list, content_sheet_list):
    # 创建一个Workbook对象，这就相当于创建了一个Excel文件
    book = xlwt.Workbook(encoding='utf-8', style_compression=0)
    sheet = book.add_sheet("sheet", cell_overwrite_ok=True)
    make_row_sheet(sheet, 0, head_sheet_list)

    row_num = 1
    for data in content_sheet_list:
        make_row_sheet(sheet, row_num, data)
        row_num += 1

    book.save("excel/%s" % (file_name))
    print("生成文件成功" + file_name)


def make_row_sheet(sheet, row_num, sheet_list):
    column_num = 0
    if isinstance(sheet_list, dict):
        for data in sheet_list.values():
            sheet.write(row_num, column_num, str(data))
            column_num += 1
    elif isinstance(sheet_list, list):
        for data in sheet_list:
            sheet.write(row_num, column_num, str(data))
            column_num += 1
    else:
        print("类型错误")


def read_excle(fileName):
    workbook = xlrd.open_workbook(fileName)
    sheet = workbook.sheet_by_index(0)
    return sheet.col_values(2, start_rowx=1)


# 新粉丝勋章人数
def new_medal():
    start1 = getYesterday(28).strftime("%Y-%m-%d") + " 00:00:00"
    end1 = getYesterday(28).strftime("%Y-%m-%d") + " 23:59:59"

    start2 = getYesterday(1).strftime("%Y-%m-%d") + " 00:00:00"
    end2 = getYesterday(1).strftime("%Y-%m-%d") + " 23:59:59"

    print("时间：" + start1, end1, start2, end2)
    read_excle("njList.xlsx")
    content_sheet_list = []
    for band in read_excle():
        user = get_user_by_band(int(band))
        if not user:
            print("错误波段号", int(band))
            continue
        nj = get_new_fansmedal(start1, end1, start2, end2, user["id"])
        content = dict()  # 空字典
        if not nj:
            content["band"] = int(band)
            content["user_name"] = user["name"]
            content["user_num"] = int(0)
        else:
            content = dict()  # 空字典
            content["band"] = int(band)
            content["user_name"] = user["name"]
            content["user_num"] = int(nj["num"])
        content_sheet_list.append(content)

    # excle信息
    file_name = getYesterday(1).strftime("%Y-%m-%d") + "新增勋章用户1.xls"
    head_sheet_list = ["波段号", "昵称", "新增粉丝勋章数量"]
    save_excel(file_name, head_sheet_list, content_sheet_list)


def top10_new_medal_exp():
    start = "2018-04-14 00:00:00"
    end = "2018-04-16 23:59:59"

    print("时间：" + start, end)
    read_excle("top10nj.xlsx")
    for band in read_excle():
        nj = get_user_by_band(int(band))
        if not nj:
            print("错误波段号", int(band))
            continue
        exp = get_top3_exp(start, end, nj["id"])
        for ex in exp:
            user_band = get_band(int(ex["user_id"]))
            print("主播波段号：" + band + "粉丝波段号：" + user_band["band"] + "经验值：" + ex["exp"])


if __name__ == "__main__":
    print("开始:", datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    try:
        new_medal()
    except Exception as e:
        print(e)
    finally:
        userDB.close()
        fansDB.close()
        print("结束:", datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
