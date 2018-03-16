#!/usr/bin/python
# -*- coding: UTF-8 -*-

# 打开一个文件
fo = open(r"C:\Users\Administrator\Desktop\script\20180313_peach.txt", "r+", encoding='UTF-8')
print("文件名: ", fo.name)
print("访问模式 : ", fo.mode)
print("是否已关闭 : ", fo.closed)
'''
#读取一个文件
for line in fo:
    print(line.strip())
fo.close();
'''



