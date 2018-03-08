# -*- coding: utf-8 -*-
#######################################################
# filename:fill_excel_date.py
# author:wuzhongming@lizhi.fm
# date:2018-01-05
# function:填充对战数据，供运营人员微调（最后录入对战数据，脚本：input_db_data.py）
#######################################################
import sys
import xlrd
import xlwt


def openExcelFile(fileName):
    """
    打开Excel文件
    """
    workBook = xlrd.open_workbook(fileName)
    return workBook


def getBattleBasicData(workBook, result, sheetName, gapList):
    sheet = workBook.sheet_by_name(sheetName)
    nCols = sheet.ncols

    start = 1  # 从第2列开始
    innerStep = 6  # 发起方主播与接收方主播，间距为6
    outerStep = 13  # 2*innerStep+1
    index = start  # 列索引
    gapIndex = 0  # gapList[gapIndex]
    resultDict = {}
    while index < nCols:
        startCol = sheet.col_values(index)
        acceptCol = sheet.col_values(index + innerStep)
        key = tuple(startCol[0:2])
        realStartCol = [int(x) for x in startCol[4::gapList[gapIndex]] if x != '']
        realAcceptCol = [int(x) for x in acceptCol[4::gapList[gapIndex]] if x != '']
        index += outerStep
        gapIndex += 1
        resultDict[key] = {'start': realStartCol, 'accept': realAcceptCol}
    result[sheetName] = resultDict
    return resultDict


def getBillboardMap(fileName, targetRows=150):
    """
    获取榜单数据
    """
    workBook = openExcelFile(fileName)
    sheetNames = workBook.sheet_names()
    resultDict = {}
    for sheetName in sheetNames:
        sheet = workBook.sheet_by_name(sheetName)
        resultList = []
        for row in range(1, min(targetRows + 1, sheet.nrows + 1)):
            rowData = sheet.row_values(row)
            resultList.append(rowData)
        resultDict[sheetName] = resultList
    return resultDict


def buildData(workBook, sheetName, sheetData):
    """
    依据数据重建Excel文件
    :param workBook:
    :param sheetName:
    :param sheetData:
    :return:
    """
    sheet = workBook.add_sheet(sheetName, cell_overwrite_ok=True)
    colIndex = 0
    for (title, battleListMap) in sheetData.items():
        rowIndex = 0
        sheet.write_merge(rowIndex, rowIndex, colIndex, colIndex + 13, title[0])
        rowIndex += 1
        sheet.write_merge(rowIndex, rowIndex, colIndex, colIndex + 13, title[1])
        rowIndex += 1
        sheet.write_merge(rowIndex, rowIndex, colIndex, colIndex + 6, '发起方主播')
        sheet.write_merge(rowIndex, rowIndex, colIndex + 7, colIndex + 13, '接收方主播')
        rowIndex += 1
        t = ['编号', '主播号', '波段号', '主播ID', '所属家族', '荔枝数', '家族ID']
        for i in range(len(t)):
            sheet.write(rowIndex, colIndex + i, t[i])
            sheet.write(rowIndex, colIndex + i + len(t), t[i])
        rowIndex += 1

        startList = battleListMap['start']
        acceptList = battleListMap['accept']
        for i in range(len(startList)):
            startUser = userList[startList[i] - 1]
            acceptUser = userList[acceptList[i] - 1]
            # 发起方主播
            sheet.write(rowIndex, colIndex + 0, startList[i])  # 编号
            sheet.write(rowIndex, colIndex + 1, startUser[1])  # 主播号
            sheet.write(rowIndex, colIndex + 2, startUser[0])  # 波段号
            sheet.write(rowIndex, colIndex + 3, startUser[2])  # 主播ID
            sheet.write(rowIndex, colIndex + 4, startUser[5])  # 所属家族
            sheet.write(rowIndex, colIndex + 5, startUser[3])  # 荔枝数
            sheet.write(rowIndex, colIndex + 6, startUser[6])  # 家族ID，不存在是为-1
            # 接收方主播
            sheet.write(rowIndex, colIndex + 7, acceptList[i])
            sheet.write(rowIndex, colIndex + 8, acceptUser[1])
            sheet.write(rowIndex, colIndex + 9, acceptUser[0])
            sheet.write(rowIndex, colIndex + 10, acceptUser[2])
            sheet.write(rowIndex, colIndex + 11, acceptUser[5])
            sheet.write(rowIndex, colIndex + 12, acceptUser[3])
            sheet.write(rowIndex, colIndex + 13, acceptUser[6])
            rowIndex += 1
        colIndex += 15  # 2*7+1


if __name__ == '__main__':
    battleFileName = 'battle.xlsx'
    billboardFileName = 'billboard.xls'
    if len(sys.argv) == 2:
        battleFileName = sys.argv[1]
    if len(sys.argv) == 3:
        battleFileName = sys.argv[1]
        billboardFileName = sys.argv[2]

    workBook = openExcelFile(battleFileName)
    allDict = {}
    getBattleBasicData(workBook, allDict, '对战模板A', [3, 3, 3, 1, 2, 1, 3, 3, 3])
    getBattleBasicData(workBook, allDict, '对战模板B', [2, 1, 1, 2, 1, 2])
    billboardMap = getBillboardMap(billboardFileName)

    for (billboardName, userList) in billboardMap.items():
        workBook = xlwt.Workbook()
        for sheetName in ['对战模板A', '对战模板B']:
            buildData(workBook, sheetName, allDict[sheetName])
        workBook.save(billboardName + '.xls')
