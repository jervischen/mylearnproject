# -*- coding: utf-8 -*-
import xlrd

xlsfile = r"D:\test1.xls"  # 打开指定路径中的xls文件
book = xlrd.open_workbook(xlsfile)  # 得到Excel文件的book对象，实例化对象
print(book.sheet_names)
sheet0 = book.sheet_by_index(2)  # 通过sheet索引获得sheet对象
print("sheet==", sheet0)
sheet_name = book.sheet_names()[0]  # 获得指定索引的sheet表名字
print("sheet_name==", sheet_name)
sheet1 = book.sheet_by_name(sheet_name)  # 通过sheet名字来获取，当然如果知道sheet名字就可以直接指定
nrows = sheet1.nrows  # 获取行总数
print("sheet_rows==", nrows)
# 循环打印每一行的内容
for i in range(nrows):
    print(sheet1.row_values(i))
ncols = sheet0.ncols  # 获取列总数
print("ncols==", ncols)
row_data = sheet0.row_values(0)  # 获得第1行的数据列表
print("row_data==",row_data)
col_data = sheet0.col_values(0)  # 获得第1列的数据列表
print("col_data==", col_data)
# 通过坐标读取表格中的数据
cell_value1 = sheet0.cell_value(0, 0)
print("cell_value1==", cell_value1)
cell_value2 = sheet0.cell_value(0, 1)
print("cell_value2==", cell_value2)
