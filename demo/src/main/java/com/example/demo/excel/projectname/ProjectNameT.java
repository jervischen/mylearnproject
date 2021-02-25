package com.example.demo.excel.projectname;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.excel.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.testng.collections.Lists;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-05-18 11:10
 */
public class ProjectNameT {

    public static void main(String[] args) {
        String path = "/Users/chenxiao/Desktop/重要文档/签约/银行地区编码.xlsx";

        List<String> list = ReadExcelUtil.readExcel(0, path);


    }

    @Test
    public void regionCode() {
        String path = "/Users/chenxiao/Desktop/重要文档/签约/银行地区编码.xlsx";
        Workbook workbook = ReadExcelUtil.readExcel(path);

        Sheet sheet = workbook.getSheetAt(0);

        List<RegionCode> regionCodeList = Lists.newArrayList();

        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            RegionCode regionCode = new RegionCode();
            regionCode.setProvinceCode(String.valueOf(row.getCell(3)));
            regionCode.setProvinceName(String.valueOf(row.getCell(4)));

            for (int j = i; j < rownum; j++) {
                Row row1 = sheet.getRow(j);
                if (regionCode.getProvinceCode().equals(String.valueOf(row1.getCell(3)))) {
                    RegionCode.Area area = new RegionCode.Area();
                    area.setCityCode(String.valueOf(row1.getCell(1)));
                    area.setCityName(String.valueOf(row1.getCell(2)));
                    regionCode.getArea().add(area);
                }
            }

            if (!regionCodeList.contains(regionCode)) {
                regionCodeList.add(regionCode);
            }
        }
        System.out.println(JSONArray.toJSON(regionCodeList));
    }

    @Test
    public void bankCode() {
        String path = "/Users/chenxiao/Desktop/重要文档/签约/银行地区编码.xlsx";
        Workbook workbook = ReadExcelUtil.readExcel(path);

        Sheet sheet = workbook.getSheetAt(1);

        List<BankCode> regionCodeList = Lists.newArrayList();

        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);

            BankCode bankCode = new BankCode();
            bankCode.setBankCode(ReadExcelUtil.getCellFormatValue(row.getCell(0)));
            bankCode.setBankName(row.getCell(1).getStringCellValue());

            regionCodeList.add(bankCode);
        }

        System.out.println(JSONArray.toJSON(regionCodeList));
    }

    @Test
    public void js() {
        String result = "{\"code\":\"0\",\"message\":\"请求成功\",\"data\":{\"value\":\"https://api.bestsign.info/sso/login?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzc3FBY2NvdW50IjoiMTM3OTg5NTk2MTUiLCJjbGllbnRJZCI6IjE1NjE1NTgyOTkwMTg3MzAwODciLCJkZXZBY2NvdW50SWQiOiJQUExJVkVfMTI3ODc4Nzk5IiwiaXNOZXdIeWJyaWQiOiJmYWxzZSIsImlzcyI6ImJlc3RzaWduIiwidXNlclR5cGUiOiIxIiwiZXhwIjoxNTkxMjc1NjgxLCJ0YXJnZXRQYWdlIjoicGVyc29uQXV0aCJ9.2fZwL2OLM6Ugsn65VZMcdLVMp9-UW_maJ-MMGfsoDC8\"}}";
        SignResult<JSONObject> signResult = JSONObject.parseObject(result, SignResult.class);
        System.out.println(signResult);
    }

    @Test
    public void dem(){
        String str1="0.02";
        BigDecimal bd=new BigDecimal(str1);
        System.out.println(bd);
        BigDecimal multiply = bd.multiply(new BigDecimal(100));
        System.out.println(multiply.intValue());
    }
}
