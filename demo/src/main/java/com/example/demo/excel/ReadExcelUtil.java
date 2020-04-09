package com.example.demo.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-04-08 10:50
 */
public class ReadExcelUtil {
    public static void main(String[] args) throws IOException {


        readExcel(0);
    }


    public static List<String> readExcel(int sheetNum) {
        List<String> list = new ArrayList<>();
        String filePath = "/Users/chenxiao/IdeaProjects/mylearnproject/demo/src/main/java/com/example/demo/excel/pp.xlsx";
        Workbook wb = readExcel(filePath);
        if (wb != null) {
            //获取第一个sheet
            Sheet sheet = wb.getSheetAt(sheetNum);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            for (int i = 1; i < rownum; i++) {
                Row row = sheet.getRow(i);
                String value = String.valueOf(row.getCell(0));
                if (StringUtils.isBlank(value)){
                    continue;
                }
                list.add(value);
//                System.out.println(value);
            }
        }

        return list;
    }

    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {

                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }


}
