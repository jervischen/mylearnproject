import pymysql
import time

"""
对mysql的封装
"""


class MySql:
    error_code = ''  # MySQL错误号码

    _instance = None  # 本类的实例
    _conn = None  # 数据库conn
    _cur = None  # 游标

    _TIMEOUT = 30  # 默认超时30秒
    _timecount = 0

    def __init__(self, dbconfig):
        try:
            self._conn = pymysql.connect(host=dbconfig['host'],
                                         port=dbconfig['port'],
                                         user=dbconfig['user'],
                                         password=dbconfig['password'],
                                         database=dbconfig['database'],
                                         charset='utf-8',
                                         cursorclass=pymysql.cursors.DictCursor)
        except pymysql.Error as e:
            self.error_code = e.args[0]
            error_msg = 'MySQL error! ', e.args[0], e.args[1]
            print(error_msg)

            # 如果没有超过预设超时时间，则再次尝试连接，
            if self._timecount < self._TIMEOUT:
                interval = 5
                self._timecount += interval
                time.sleep(interval)
                return self.__init__(dbconfig)
            else:
                raise Exception(error_msg)

    def query(self, sql):
        u'执行 select 语句'
        try:
            self._cur.execute("SET NAMES utf8")
            result = self._cur.execute(sql)
        except pymysql.Error as e:
            self.error_code = e.args[0]
            print ("数据库错误代码:", e.args[0], e.args[1])
            print (sql)
            result = False
        return result

    def queryWithParams(self, sql, param):
        u'执行 SELECT 语句'
        try:
            self._cur.execute("SET NAMES utf8")
            result = self._cur.execute(sql, param)
        except pymysql.Error as e:
            self.error_code = e.args[0]
            print ("数据库错误代码:", e.args[0], e.args[1])
            print (sql)
            result = False
        return result

    def update(self, sql, param):
        u'执行 UPDATE 及 DELETE 语句'
        try:
            self._cur.execute("SET NAMES utf8")
            result = self._cur.execute(sql, param)
            self._conn.commit()
        except pymysql.Error as e:
            self.error_code = e.args[0]
            print ("数据库错误代码:", e.args[0], e.args[1])
            result = False
        return result

    def insert(self, sql, param):
        u'执行 INSERT 语句。如主键为自增长int，则返回新生成的ID'
        try:
            self._cur.execute("SET NAMES utf8")
            self._cur.execute(sql, param)
            self._conn.commit()
            return self._conn.insert_id()
        except pymysql.Error as e:
            self.error_code = e.args[0]
            return False

    def insertBatch(self, sql, param):
        u'执行 批量INSERT 语句'
        try:
            self._cur.execute("SET NAMES utf8")
            self._cur.executemany(sql, param)
            self._conn.commit()
        except pymysql.Error as e:
            self.error_code = e.args[0]
            return False

    def fetchAllRows(self):
        u'返回结果列表'
        return self._cur.fetchall()

    def fetchOneRow(self):
        u'返回一行结果，然后游标指向下一行。到达最后一行以后，返回None'
        return self._cur.fetchone()

    def getRowCount(self):
        u'获取结果行数'
        return self._cur.rowcount

    def commit(self):
        u'数据库commit操作'
        self._conn.commit()

    def rollback(self):
        u'数据库回滚操作'
        self._conn.rollback()

    def __del__(self):
        u'释放资源（系统GC自动调用）'
        try:
            self._cur.close()
            self._conn.close()
        except:
            pass

    def close(self):
        u'关闭数据库连接'
        self.__del__()

    if __name__ == '__main__':
        '''使用样例'''

        # 数据库连接参数
        dbconfig = {'host': 'localhost',
                    'port': 3306,
                    'user': 'root',
                    'passwd': '',
                    'db': 'testdb',
                    'charset': 'utf8'}

        # 连接数据库，创建这个类的实例
        db = MySql(dbconfig)

        # 操作数据库
        sql = "SELECT * FROM `chentest`"
        db.query(sql)

        # 获取结果列表
        result = db.fetchAllRows()

        # 相当于php里面的var_dump
        print(result)

        # 对行进行循环
        for row in result:
            # 使用下标进行取值
            # print row[0]

            # 对列进行循环
            for colum in row:
                print(colum)

        # 关闭数据库
        db.close()
