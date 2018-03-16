import pymysql

host = "172.17.6.232"
port = 3306
user = "fmuser"
password = "fmpass"


# host = "localhost"
# port = 3306
# user = "root"
# password = ""

def getMySQLConnection(host, port, user, password, database="lizhi_app", charset='utf8'):
    """
    建立数据库连接
    """
    return pymysql.connect(host=host, port=port, user=user, password=password, database=database, charset=charset,
                           cursorclass=pymysql.cursors.DictCursor)


# 查询数据
connection = getMySQLConnection(host, port, user, password)
cursor_ex = connection.cursor()
sql_str = "SELECT radio.* FROM radio_nj JOIN radio ON radio_nj.radio_id=radio.id AND radio_nj.user_id=%d"%(2636229944249696812)
s = cursor_ex.execute(sql_str)
row_band = cursor_ex.fetchone()
print(row_band['band'])

sql_str2 = "SELECT * FROM USER WHERE id=%d"
s = cursor_ex.execute(sql_str2%(2636229944249696812))
row_user = cursor_ex.fetchone()
print(row_user['name'])

# 提交，不然无法保存新建或者修改的数据
connection.commit()
# 关闭游标
cursor_ex.close()
# 关闭连接
connection.close()
