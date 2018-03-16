# -*- coding: UTF-8 -*-

# 打开一个文件
# fo = open(r"C:\Users\Administrator\Desktop\script\20180313_peach.txt", "r+", encoding='UTF-8')
# print("文件名: ", fo.name)
# print("访问模式 : ", fo.mode)
# print("是否已关闭 : ", fo.closed)
# fo.close()

'''
#读取一个文件
for line in fo:
    print(line.strip())
fo.close();
'''

# 打开一个文件
try:
    f = open("/tmp/test_file.txt", "w")

    f.write("Python 是一个非常好的语言。\n是的，的确非常好!!\n")
except IOError as e:
    print("异常。。",e)
else:
    # 关闭打开的文件
    f.close()
