# -*- coding: utf-8 -*-
#导入xlwt模块
import xlrd



if __name__ == "__main__":
    workbook = xlrd.open_workbook("rank.xls")
    sheets = workbook.sheets()

    #获取整个sheet
    sheet = workbook.sheet_by_index(0)
    #获取所有行
    print(sheet.get_rows().__next__().pop(3))
    print(sheet.col_values(3))
    print(type(sheet.row(0)[3]))
    print((sheet.row(0)[3]).value)
    print((sheet.row(1)[3]).value)
    print(sheet.nrows)


