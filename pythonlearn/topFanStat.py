#!/usr/bin/env python
# coding=utf-8
__author__ = 'LiZHi'

import time
import sys

reload(sys)
sys.setdefaultencoding('utf-8')

# 初始化数据库
# ANCHOR_DB_CONFIG = {
#     'host': 'operate_db_server.lzfm.com',
#     'user': 'mon_appbm',
#     'passwd': 'LZ#bm2man5',
#     'port': 3306,
#     'dbName': 'lizhi_app'}
# GIFT_DB_CONFIG = {
#     'host': 'r_live_gift_db_server.lzfm.com',
#     'user': 'livegift_r',
#     'passwd': 'liveR0912GIFT',
#     'port': 3341,
#     'dbName': 'lizhi_live_gift'}
ANCHOR_DB_CONFIG = {
    'host': '172.17.6.232',
    'user': 'fmuser',
    'passwd': 'fmpass',
    'port': 3306,
    'dbName': 'lizhi_app'}
GIFT_DB_CONFIG = {
    'host': '172.17.6.232',
    'user': 'fmuser',
    'passwd': 'fmpass',
    'port': 3306,
    'dbName': 'lizhi_live_gift'}
from LiZhiAnchorSQL import LiZhiAnchorSQL
from LiZhiGiftSQL import LiZhiGiftSQL

anchorDB = LiZhiAnchorSQL(ANCHOR_DB_CONFIG)
giftDB = LiZhiGiftSQL(GIFT_DB_CONFIG)

# just do it
if __name__ == "__main__":
    # 开始时间
    begin_time = int(time.time())
    print("begin time:%d" % begin_time)

    # nj_band_list = ["2557430", "2361949", "23499190", "35785992", "3180554", "1908329", "1875731", "1591106", "31214668", "79280130", "1685623", "28680306", "2993850", "1853054", "2056484", "3184408", "68949316"]
    # begin_time1 = '2018-05-28 00:00:00'
    # end_time1 = '2018-05-29 00:00:00'
    # begin_time2 = '2018-05-29 00:00:00'
    # end_time2 = '2018-05-30 00:00:00'

    nj_band_list = ["517176", "25681"]
    begin_time1 = '2016-06-07 00:00:00'
    end_time1 = '2016-06-08 00:00:00'
    begin_time2 = '2016-06-08 00:00:00'
    end_time2 = '2016-06-09 00:00:00'

    #day1
    print begin_time1 + '-' + end_time1
    for nj_band in nj_band_list:
        nj_id = anchorDB.get_user_id(nj_band, 'band')
        top_fan_info = giftDB.get_top_fan(nj_id, begin_time1, end_time1)
        total_litchi_amount = top_fan_info.get('total_litchi_amount', '')
        fan_id = top_fan_info.get('user_id', '')
        fan_radio_info = anchorDB.get_radio_info(fan_id)
        fan_band = fan_radio_info.get('band', '')
        print nj_band + ',' + str(fan_band) + ',' + str(total_litchi_amount)

    #day12
    print begin_time2 + '-' + end_time2
    for nj_band in nj_band_list:
        nj_id = anchorDB.get_user_id(nj_band, 'band')
        top_fan_info = giftDB.get_top_fan(nj_id, begin_time2, end_time2)
        total_litchi_amount = top_fan_info.get('total_litchi_amount', '')
        fan_id = top_fan_info.get('user_id', '')
        fan_radio_info = anchorDB.get_radio_info(fan_id)
        fan_band = fan_radio_info.get('band', '')
        print nj_band + ',' + str(fan_band) + ',' + str(total_litchi_amount)

    # 关闭数据库连接
    anchorDB.close()
    giftDB.close()
    # 结束时间
    end_time = int(time.time())
    print('end time:%d' % end_time)
    print('total time:%d' % (end_time - begin_time))
