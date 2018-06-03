#!/usr/bin/env python
# coding=utf-8
__author__ = 'LiZHi'

import time
# import datetime
# import sys
import redis

# reload(sys)
# sys.setdefaultencoding('utf-8')

# 初始化数据库
# from payment_conf import PAYMENT_CONFIGS
# ANCHOR_DB_CONFIG = {
#     'host': 'operate_db_server.lzfm.com',
#     'user': 'mon_appbm',
#     'passwd': 'LZ#bm2man5',
#     'port': 3306,
#     'dbName': 'lizhi_app'}
ANCHOR_DB_CONFIG = {
    'host': '172.17.6.232',
    'user': 'fmuser',
    'passwd': 'fmpass',
    'port': 3306,
    'dbName': 'lizhi_app'}
from LiZhiAnchorSQL import LiZhiAnchorSQL

anchorDB = LiZhiAnchorSQL(ANCHOR_DB_CONFIG)

# just do it
if __name__ == "__main__":
    # 开始时间
    begin_time = int(time.time())
    print("begin time:%d" % begin_time)
    # redisClient = redis.StrictRedis(host='live-operating-act-6124-rds-ser.service.dbconsul', port=6124)
    redisClient = redis.StrictRedis(host='172.17.6.232', port=6379)
    njRank = 'LZ_LIVE_ACTIVITY_RANK_SORTED_SET_CUPID_NJ'
    userRank = 'LZ_LIVE_ACTIVITY_RANK_SORTED_SET_CUPID_USER'
    numAchieveBase = 'LZ_LIVE_ACTIVITY_USER_ADDTION_CUPID_ACHIEVE_NUM_COLLECT_'
    numAchieveField = 'ACHIEVEMENT_STATUS'

    # 收到一个以上任一福袋的主播数据
    nj_sort_list = redisClient.zrevrange(njRank, 0, -1, True)
    if nj_sort_list and len(nj_sort_list):
        for sort_info in nj_sort_list:
            user_id = sort_info[0]
            score = sort_info[1]
            # get radio_info
            radio_info = anchorDB.get_radio_info(user_id)
            if not radio_info:
                print ("f:dispatch,user_id:%s,radio info is None" % user_id)
                continue
            print (radio_info.get('name', '') + ',' + radio_info.get('band', '') + ',' + str(score))

    # 完成三个成就、五个成就的人数
    num_achievement_three = 0
    num_achievement_five = 0
    user_sort_list = redisClient.zrevrange(userRank, 0, -1, True)
    if user_sort_list and len(user_sort_list):
        for sort_info in user_sort_list:
            user_id = sort_info[0]
            score = sort_info[1]
            # get radio_info
            radio_info = anchorDB.get_radio_info(user_id)
            if not radio_info:
                print ("f:dispatch,user_id:%s,radio info is None" % user_id)
                continue
            print (radio_info.get('band', '') + ',' + str(score))
            num_achievement = redisClient.hget(numAchieveBase + user_id, numAchieveField)
            if num_achievement >= 3:
                num_achievement_three += 1
            if num_achievement >= 5:
                num_achievement_five += 1
            print(radio_info.get('name', '') + ',' + radio_info.get('band', '') + ',' + "," + str(score) + "," + str(
                num_achievement))
        print("reach 3:" + str(num_achievement_three))
        print("reach 5:" + str(num_achievement_five))
    # 关闭数据库连接
    anchorDB.close()
    # 结束时间
    end_time = int(time.time())
    print("end time:%d" % end_time)
    print("total time:%d" % (end_time - begin_time))
