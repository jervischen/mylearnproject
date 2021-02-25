package com.example.demo.excel.family;

import com.example.demo.excel.ReadExcelUtil;
import com.example.demo.util.DateUtil;
import com.example.demo.util.HttpClientUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author Chen Xiao
 * @since 2020-07-16 10:31
 */
public class PPFamily {


    public static void main(String[] args) {
//        Workbook workbook = ReadExcelUtil.readExcel("/Users/chenxiao/IdeaProjects/mylearnproject/demo/src/main/java/com/example/demo/excel/family/需同步合同721.xlsx");
        Workbook workbook = ReadExcelUtil.readExcel("/Users/chenxiao/IdeaProjects/mylearnproject/demo/src/main/java/com/example/demo/excel/family/需录入签约后台0927.xlsx");

        Sheet sheet = workbook.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            Map<String, Object> param = new HashMap<>();
            param.put("njId", ReadExcelUtil.getCellFormatValue(row.getCell(0)));
            param.put("familyId", ReadExcelUtil.getCellFormatValue(row.getCell(9)));
            //计算月份
            String beginTime = String.valueOf(row.getCell(12));
            String endTime = String.valueOf(row.getCell(13));
            if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
                System.out.println(String.format("合同未完成序号=%s,njId=%s", String.valueOf(row.getCell(0)), String.valueOf(row.getCell(1))));
                continue;
            }

            int monthDiff = DateUtil.getMonthDiff(DateUtil.parseSimple(endTime), DateUtil.parseSimple(beginTime));
            param.put("duration", monthDiff);
            param.put("percentage", 100);
            param.put("contractNo", ReadExcelUtil.getCellFormatValue(row.getCell(10)));
            param.put("beginTime", beginTime);
            param.put("endTime", endTime);
            if (((String) param.get("contractNo")).contains("合同")) {
                System.out.print("序号:" + ReadExcelUtil.getCellFormatValue(row.getCell(0)));
                System.out.println(param);
                continue;
            }
            String result = HttpClientUtil.doPost("http://familyh5pre.pparty.com/api/family/syncOldContract", param);
//            String result = HttpClientUtil.doPost("http://ppfamily.yfxn.lizhi.fm/api/family/syncOldContract", param);
            System.out.println(param);
            System.out.println(result);
        }
    }
}
