#!/usr/bin/env python
# -*- coding: utf-8 -*-
u'''对获取Lizhi主播相关信息常用函数进行封装的类
'''

from MySQL import MySQL


class LiZhiAnchorSQL:

    db=None #数据库对象
    TYPE_BAND = 'band'
    TYPE_EMAIL = 'email'
    TYPE_PHONENUM = 'phonenum'

    def __init__(self, dbConfig):
        u'构造器：根据数据库连接参数，创建MySQL连接'
        self.db = MySQL(dbConfig)

    def close(self):
        u'关闭数据库连接'
        self.db.close()

    u'''获取播客名称(通过用户ID)
    '''
    def get_radio_info(self, user_id):

        sql='''
            SELECT band,name,id FROM lizhi_app.radio WHERE id=(SELECT radio_id FROM lizhi_app.radio_nj WHERE user_id=%s LIMIT 1)
        '''
        params=([user_id])
        self.db.queryWithParams(sql, params);
        data_info = self.db.fetchOneRow();
        if data_info:
            band=data_info[0].encode('utf-8')
            name=data_info[1].encode('utf-8')
            id=data_info[2]
            return {"band":band,"name":name,"id":id}
        else:
            return {"band":0,"name":0,"id":0}

    u'''获取播客名称(通过波段号)
        '''

    def get_radio_info_by_band(self, band):

        sql = '''
                SELECT band,name,id FROM lizhi_app.radio WHERE band=%s
            '''
        params = (band)
        self.db.queryWithParams(sql, params);
        data_info = self.db.fetchOneRow();
        if data_info:
            band = data_info[0].encode('utf-8')
            name = data_info[1].encode('utf-8')
            id = data_info[2]
            return {"band": band, "name": name, "id": id}

    u'''获取主播信息
    '''
    def get_nj_info(self, user_id):
        sql='''
            SELECT name, email, phonenum FROM
                lizhi_app.user WHERE id=%s
        '''
        params = (user_id)
        self.db.queryWithParams(sql, params)
        data_info = self.db.fetchOneRow()
        name = ''
        email = ''
        phonenum = ''
        if data_info:
            name = data_info[0].encode('utf-8')
            if data_info[1]:
                email=data_info[1].encode('utf-8')
	        if data_info[2]:
		        phonenum=data_info[2].encode('utf-8')
        return {"name":name,"email":email,"phonenum":phonenum}

    u'''获取播客的分类信息
    '''
    def get_radio_label_info(self, radio_id):
        sql='''
            SELECT id as label_id,label_class_id,name as label_name,(SELECT name FROM lizhi_app.label_class WHERE id=label_class_id) as label_class_name FROM
                lizhi_app.label WHERE id=(SELECT label_id from lizhi_app.radio_label where radio_id=%s)
        '''
        params = (radio_id)
        self.db.queryWithParams(sql, params);
        data_info = self.db.fetchOneRow();
        if data_info:
            label_id=data_info[0]
            label_class_id=data_info[1]
            label_name=data_info[2].encode('utf-8')
            label_class_name=data_info[3].encode('utf-8')
            return {"label_id":label_id,"label_class_id":label_class_id,"label_name":label_name,"label_class_name":label_class_name}

    u'''获取用户ID
    '''
    def get_user_id(self, account, type):
        if type==self.TYPE_BAND:
            sql='''
                SELECT user_id FROM
                    lizhi_app.radio_nj WHERE radio_id=(SELECT id FROM lizhi_app.radio WHERE band=%s)
            '''
        elif type==self.TYPE_EMAIL:
            sql='''
                SELECT id FROM
                    lizhi_app.user WHERE email=%s
            '''
        elif type==self.TYPE_PHONENUM:
            if "86-" not in account:
                account="86-%s" % account
            sql='''
                SELECT id FROM
                    lizhi_app.user WHERE phonenum=%s
            '''
        else:
            print "no match"
            return
        params = ([str(account)])
        self.db.queryWithParams(sql, params)
        data_info = self.db.fetchOneRow()
        id=None
        if data_info:
            id=data_info[0]
        else:
            print sql
        return id

    u'''获取主播信息
       '''

    def get_radio_label(self, user_id):
        sql = '''
              SELECT label_id FROM lizhi_app.radio_label WHERE radio_id=(SELECT radio_id FROM lizhi_app.radio_nj WHERE user_id=%s)
           '''

        params = (user_id)
        self.db.queryWithParams(sql, params)
        data_info = self.db.fetchOneRow()
        if data_info:
            return data_info[0]
