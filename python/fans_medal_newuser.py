# -*- coding:utf-8 -*-
import datetime
from MySqlUtil import MySql
import xlwt
import xlrd

'''导出新增粉丝勋章数量'''
fansDB = MySql(
    {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'live_fans_level'})

userDB = MySql(
    {'host': '172.17.6.232', 'port': 3306, 'user': 'fmuser', 'passwd': 'fmpass', 'dbName': 'lizhi_app'})


def get_medal_new_user(start, end):
    select_sql = """
            SELECT new_medal.nj_id,COUNT(new_medal.user_id) num FROM
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time < '%s'
        GROUP BY nj_id,user_id HAVING SUM(increase_exp) > 0) have_medal
        RIGHT JOIN
        (SELECT nj_id,user_id FROM fans_exp_history 
        WHERE create_time BETWEEN '%s' AND '%s' 
        GROUP BY nj_id,user_id HAVING SUM(increase_exp) > 0) new_medal
        ON have_medal.nj_id = new_medal.nj_id AND have_medal.user_id = new_medal.user_id
        WHERE have_medal.user_id IS NULL
        GROUP BY new_medal.user_id
     """
    fansDB.query(select_sql % (start, start, end))
    return fansDB.fetchAllRows()


def get_username(user_id):
    sql_user = "SELECT * FROM USER WHERE id=%d"
    userDB.query(sql_user % int(user_id))
    return userDB.fetchOneRow()


def get_band(user_id):
    sql_band = "SELECT radio.* FROM radio_nj JOIN radio ON radio_nj.radio_id=radio.id AND radio_nj.user_id=%d"
    userDB.query(sql_band % int(user_id))
    return userDB.fetchOneRow()


def getYesterday():
    today = datetime.date.today()
    oneday = datetime.timedelta(days=1)
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

    book.save(file_name)
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



if __name__ == "__main__":
    try:
        yesterday = getYesterday()
        start = yesterday.strftime("%Y-%m-%d") + " 00:00:00"
        end = yesterday.strftime("%Y-%m-%d") + " 23:59:59"
        print("时间：" + start, end)

        content_sheet_list = []
        medal_new_user = get_medal_new_user(start, end)
        print(medal_new_user)
        for nj in medal_new_user:
            user = get_username(nj["nj_id"])
            band = get_band(nj["nj_id"])

            content = dict()  # 空字典
            content["band"] = band["band"]
            content["user_name"] = user["name"]
            content["user_num"] = nj["num"]
            content_sheet_list.append(content)

        print(content_sheet_list)
        # excle信息
        file_name = yesterday.strftime("%Y-%m-%d") + "新增勋章用户.xls"
        head_sheet_list = ["波段号", "昵称", "新增粉丝勋章数量"]
        save_excel(file_name, head_sheet_list, content_sheet_list)
    except Exception as e:
        print(e)
    finally:
        userDB.close()
        fansDB.close()
