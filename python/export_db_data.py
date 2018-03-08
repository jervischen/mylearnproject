# -*- coding: utf-8 -*-
#######################################################
# filename:export_db_data.py
# author:wuzhongming@lizhi.fm
# date:2018-01-08
# function:导出数据库数据
#######################################################
import pymysql
import time

import xlwt


def getMySQLConnection(host, port, user, password, database="lizhi_liveact", charset='utf8'):
    """
    建立数据库连接
    """
    return pymysql.connect(host=host, port=port, user=user, password=password, database=database, charset=charset,
                           cursorclass=pymysql.cursors.DictCursor)


def fetchData(connection, range_start_time, range_end_time):
    """
    依据PK开始时间和PK结束时间获取数据
    :param connection: 数据库连接
    :param range_start_time: PK起始时间
    :param range_end_time: PK结束时间
    :return:
    """
    try:
        cursor = connection.cursor()
        SQL = "SELECT * FROM pk_king_hist WHERE range_start_time >= '" + range_start_time + "' AND range_end_time < '" + range_end_time + "'"
        cursor.execute(SQL)
        return cursor.fetchall()
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        connection.close()


def buildData(dataList=[]):
    """
    生成Excel数据
    """
    workBook = xlwt.Workbook()
    sheet = workBook.add_sheet("Sheet1", cell_overwrite_ok=True)
    sheet.write(0, 0, '发起方主播ID')
    sheet.write(0, 1, '发起方主播波段号')
    sheet.write(0, 2, '发起方主播家族ID')
    sheet.write(0, 3, '接受方主播ID')
    sheet.write(0, 4, '接受方主播波段号')
    sheet.write(0, 5, '接受方主播家族ID')
    sheet.write(0, 6, 'PK开始时间')
    sheet.write(0, 7, 'PK结束时间')
    rowIndex = 1
    for data in dataList:
        sheet.write(rowIndex, 0, data['target_a_uid'])
        sheet.write(rowIndex, 1, data['target_a_band'])
        sheet.write(rowIndex, 2, data['target_a_family_id'])
        sheet.write(rowIndex, 3, data['target_b_uid'])
        sheet.write(rowIndex, 4, data['target_b_band'])
        sheet.write(rowIndex, 5, data['target_b_family_id'])
        sheet.write(rowIndex, 6, time.strftime(FORMAT, time.localtime(int(data['range_start_time']) / 1000)))
        sheet.write(rowIndex, 7, time.strftime(FORMAT, time.localtime(int(data['range_end_time']) / 1000)))
        rowIndex += 1
    workBook.save('export.xls')


if __name__ == '__main__':
    host = "r_liveact_db_server.lzfm.com"
    port = 3310
    user = "read_liveact"
    password = "liveact!#.com"
    # host = "localhost"
    # port = 3306
    # user = "root"
    # password = ""

    FORMAT = '%Y-%m-%d %H:%M:%S'
    range_start_time = str(int(time.mktime(time.strptime('2018-01-15 00:00:00', FORMAT))) * 1000)
    range_end_time = str(int(time.mktime(time.strptime('2018-01-16 00:00:00', FORMAT))) * 1000)

    result = fetchData(getMySQLConnection(host, port, user, password), range_start_time, range_end_time)
    buildData(result)
