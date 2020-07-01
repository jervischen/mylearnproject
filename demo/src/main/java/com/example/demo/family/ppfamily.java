package com.example.demo.family;

import com.example.demo.excel.ReadExcelUtil;
import com.example.demo.excel.projectname.BankCode;
import com.example.demo.util.HttpClientUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.collections.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2020-06-29 10:46
 */
public class ppfamily {




    public static void main(String[] args) {
        String path = "/Users/chenxiao/IdeaProjects/mylearnproject/demo/src/main/java/com/example/demo/family/家族导入模板.xlsx";
        Workbook workbook = ReadExcelUtil.readExcel(path);

        Sheet sheet = workbook.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            String njId = String.valueOf(row.getCell(2)).trim();
            String familyName = String.valueOf(row.getCell(4)).trim();
            System.out.println(njId+"_"+familyName);

            Map<String, Object> param = new HashMap<>();
            param.put("userId",njId);
            param.put("familyName",familyName);
            String result = HttpClientUtil.doPost("http://ppfamily.yfxn.lizhi.fm/api/family/autoCreateFamily", param);
            System.out.println(result);
        }
    }
}
