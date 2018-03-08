# -*- coding: utf-8 -*-
#######################################################
# filename:input_db_data.py
# author:wuzhongming@lizhi.fm
# date:2018-01-06
# function:录入对战数据
#######################################################
import time
import xlrd
import pymysql
import uuid


class Entity:
    """
    数据库实体
    """
    TIME_FORMAT = '%Y-%m-%d %H:%M:%S'
    DATE_FORMAT = '%Y-%m-%d'
    DOUBLE_POINT_DATE = ('2018-01-14', '2018-01-15')
    __id = -1  # 主键，通过generateRandomMySQLId生成
    __pk_id = 0  # 默认值0
    __target_a_uid = -1  # 发起方主播ID
    __target_a_band = '-1'  # 发起方主播波段号
    __target_a_point = 2 if time.strftime(DATE_FORMAT) in DOUBLE_POINT_DATE else 1  # 14/15号双倍积分，其余时间为单倍积分
    __target_b_uid = -1  # 接受方主播ID
    __target_b_band = '-1'  # 接受方主播波段号
    __target_b_point = 2 if time.strftime(DATE_FORMAT) in DOUBLE_POINT_DATE else 1  # 14/15号双倍积分，其余时间为单倍积分
    __range_start_time = -1  # PK开始时间，Unix时间戳，单位毫秒
    __range_end_time = -1  # PK结束时间，Unix时间戳，单位毫秒
    __create_time = time.strftime(TIME_FORMAT)  # 创建时间，默认为当前时间
    __modify_time = time.strftime(TIME_FORMAT)  # 修改时间，默认为当前时间
    __status = 0  # 默认值为0
    __target_a_family_id = -1  # 发起方主播所属家族的ID
    __target_b_family_id = -1  # 接受方主播所属家族的ID

    def __init__(self, target_a_uid, target_a_band, target_b_uid, target_b_band,
                 range_start_time, range_end_time, target_a_family_id, target_b_family_id):
        self.__id = generateRandomMySQLId()
        self.__target_a_uid = target_a_uid
        self.__target_a_band = target_a_band
        self.__target_b_uid = target_b_uid
        self.__target_b_band = target_b_band
        self.__range_start_time = range_start_time
        self.__range_end_time = range_end_time
        self.__target_a_family_id = target_a_family_id
        self.__target_b_family_id = target_b_family_id

    def __str__(self):
        return '{id:%s , target_a_uid:%s , target_a_band:%s , target_b_uid:%s , target_b_band:%s , ' \
               'range_start_time:%s , range_end_time:%s , ' \
               'create_time:%s , modify_time:%s , target_a_family_id:%s , target_b_family_id:%s}' % (
                   self.__id, self.__target_a_uid, self.__target_a_band, self.__target_b_uid, self.__target_b_band,
                   self.__range_start_time, self.__range_end_time, self.__create_time, self.__modify_time,
                   self.__target_a_family_id, self.__target_b_family_id)

    @property
    def id(self):
        return self.__id

    @id.setter  # setter
    def id(self, id):
        self.__id = id

    @property
    def pk_id(self):
        return self.__pk_id

    @property
    def target_a_uid(self):
        return self.__target_a_uid

    @property
    def target_a_band(self):
        return self.__target_a_band

    @property
    def target_a_point(self):
        return self.__target_a_point

    @property
    def target_b_uid(self):
        return self.__target_b_uid

    @property
    def target_b_band(self):
        return self.__target_b_band

    @property
    def target_b_point(self):
        return self.__target_b_point

    @property
    def range_start_time(self):
        return self.__range_start_time

    @property
    def range_end_time(self):
        return self.__range_end_time

    @property
    def create_time(self):
        return self.__create_time

    @property
    def modify_time(self):
        return self.__modify_time

    @property
    def status(self):
        return self.__status

    @property
    def target_a_family_id(self):
        return self.__target_a_family_id

    @property
    def target_b_family_id(self):
        return self.__target_b_family_id


def getMySQLConnection(host, port, user, password, database="lizhi_liveact", charset='utf8'):
    """
    建立数据库连接
    """
    return pymysql.connect(host=host, port=port, user=user, password=password, database=database, charset=charset,
                           cursorclass=pymysql.cursors.DictCursor)


def generateRandomMySQLId():
    """
    MySQL随机ID生成器，底层id存储为bigint(20)，占用8Bytes，即64bit
    这里采用取UUID1（基于时间戳+Mac地址+随机数）的低63bit
    """
    return uuid.uuid1().int >> 65


def insert(connection, entityList=[]):
    """
    逐条插入
    :param entityList: 实体列表
    """
    if len(entityList) == 0:
        return
    cursor = connection.cursor()
    size = len(entityList)  # 记录总数

    BASE_SQL = "INSERT INTO pk_king_hist (id,pk_id,target_a_uid,target_a_band,target_a_point,target_b_uid,target_b_band,target_b_point," \
               "range_start_time,range_end_time,create_time,modify_time,status,target_a_family_id,target_b_family_id) VALUES "

    for i in range(size):
        entity = entityList[i]
        inserted = False  # 是否插入成功
        while not inserted:  # 直到插入成功为止
            try:
                sql = BASE_SQL
                sql = appendSQLStr(sql, entity)
                cursor.execute(sql)
                print(sql)
                connection.commit()
                inserted = True  # 插入成功
            except Exception as e:
                connection.rollback()
                print(e)
                print(sql)
                entity.id = generateRandomMySQLId()  # 重新生成主键
            finally:
                pass

    cursor.close()
    connection.close()


def appendSQLStr(sql, entity):
    """
    拼接插入SQL
    :param entity: 实体
    """
    SPILT_STR = "','"
    sql += "('"
    sql += str(entity.id) + SPILT_STR
    sql += str(entity.pk_id) + SPILT_STR
    sql += str(entity.target_a_uid) + SPILT_STR
    sql += str(int(entity.target_a_band)) + SPILT_STR
    sql += str(entity.target_a_point) + SPILT_STR
    sql += str(entity.target_b_uid) + SPILT_STR
    sql += str(int(entity.target_b_band)) + SPILT_STR
    sql += str(entity.target_b_point) + SPILT_STR
    sql += str(entity.range_start_time) + SPILT_STR
    sql += str(entity.range_end_time) + SPILT_STR
    sql += str(entity.create_time) + SPILT_STR
    sql += str(entity.modify_time) + SPILT_STR
    sql += str(entity.status) + SPILT_STR
    sql += str(0 if str(entity.target_a_family_id) in ('-1.0', '-1') else entity.target_a_family_id) + SPILT_STR
    sql += str(0 if str(entity.target_b_family_id) in ('-1.0', '-1') else entity.target_b_family_id)
    sql += ("')")
    return sql


def openExcelFile(fileName):
    """
    打开Excel文件
    """
    workBook = xlrd.open_workbook(fileName)
    return workBook


def getBattleDate():
    """
    获取对战数据，封装成数据库实体
    :param billboardFileName: 对战表
    """
    billboardNames = ['娱乐男生榜', '情感男生榜', '才艺男生榜', '娱乐女生榜', '情感女生榜', '才艺女生榜']
    entityList = []
    for billboardName in billboardNames:
        try:
            workBook = openExcelFile(billboardName + ".xls")
        except Exception as e:
            print(e)
            continue  # 对应的榜单文件不存在直接跳过
        sheetNames = workBook.sheet_names()
        for sheetName in sheetNames:
            sheet = workBook.sheet_by_name(sheetName)
            nCols = sheet.ncols
            colIndex = 0
            outerStep = 15
            while colIndex < nCols:
                # PK开始时间+PK结束时间
                FORMAT = '%Y-%m-%d %H:%M:%S'
                timeRange = sheet.col_values(colIndex)[0].split('=')
                startTime = int(time.mktime(time.strptime(timeRange[0], FORMAT))) * 1000  # PK开始时间，Unix时间戳，单位毫秒
                endTime = int(time.mktime(time.strptime(timeRange[1], FORMAT))) * 1000  # PK结束时间，Unix时间戳，单位毫秒

                # 发起方主播
                startUserBands = [x for x in sheet.col_values(colIndex + 2)[4:] if x not in ('', ' ')]  # 主播波段名列表
                startUserIds = [x for x in sheet.col_values(colIndex + 3)[4:] if x not in ('', ' ')]  # 主播ID列表
                startUserFamilyIds = [str(x) for x in sheet.col_values(colIndex + 6)[4:] if
                                      x not in ('', ' ')]  # 主播家族ID列表

                # 接受方主播
                acceptUserBands = [x for x in sheet.col_values(colIndex + 9)[4:] if x not in ('', ' ')]
                acceptUserIds = [x for x in sheet.col_values(colIndex + 10)[4:] if x not in ('', ' ')]
                acceptUserFamilyIds = [str(x) for x in sheet.col_values(colIndex + 13)[4:] if x not in ('', ' ')]

                for i in range(len(startUserIds)):
                    try:
                        entity = Entity(startUserIds[i], startUserBands[i], acceptUserIds[i], acceptUserBands[i],
                                        startTime, endTime, startUserFamilyIds[i], acceptUserFamilyIds[i])
                        entityList.append(entity)
                    except Exception as e:
                        print(e)
                        print(billboardName)
                        print(colIndex)
                        print(startUserIds)
                        print(len(startUserBands))
                        print(len(startUserIds))
                        print(len(startUserFamilyIds))
                        print(len(acceptUserBands))
                        print(len(acceptUserIds))
                        print(len(acceptUserFamilyIds))
                        exit(-1)
                    finally:
                        pass
                colIndex += outerStep
    return entityList


if __name__ == '__main__':
    host = "dbconf_dc.lzfm.com"
    port = 3310
    user = "mon_liveact"
    password = "p9D!F4nM"
    # host = "localhost"
    # port = 3306
    # user = "root"
    # password = ""

    insert(getMySQLConnection(host, port, user, password), getBattleDate())
