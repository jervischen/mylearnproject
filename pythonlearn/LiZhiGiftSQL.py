#!/usr/bin/env python
# -*- coding: utf-8 -*-
u'''对获取Lizhi直播礼物相关信息常用函数进行封装的类
'''

from MySQL import MySQL


class LiZhiGiftSQL:

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

    u'''日榜TOP1粉丝（通过用户id，筛选时间）
    '''
    def get_top_fan(self, user_id, begin_time, end_time):

        sql='''
            SELECT user_id,IFNULL(sum(total_litchi_amount),0) AS total_litchi_amount
            FROM live_give_gift_action
            WHERE nj_id = %s AND create_time between %s AND %s group by user_id 
            order by total_litchi_amount desc limit 1
        '''
        params=([user_id, begin_time, end_time])
        self.db.queryWithParams(sql, params);
        data_info = self.db.fetchOneRow();
        if data_info:
            user_id = data_info[0]
            total_litchi_amount = data_info[1]
            return {"user_id":user_id,"total_litchi_amount":total_litchi_amount}
        else:
            return {"user_id":0,"total_litchi_amount":0}
