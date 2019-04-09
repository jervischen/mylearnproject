package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.bean.CategoryVo;
import com.example.demo.bean.Content;
import com.example.demo.bean.MyData;
import com.example.demo.bean.MyMsg;
import com.example.demo.bean.SocialRedEnvelopeMsg;
import com.example.demo.util.DateUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created in 2018-02-01 19:57.
 *
 * @author chenxiao
 */
public class TestJson {
    private static Logger logger = LoggerFactory.getLogger(TestJson.class);

    @Test
    public void testJsonToObject() {
        String json = "[{\"full\":true,\"urlShareable\":true}]";
        List<MyData> myData = JSONObject.parseArray(json, MyData.class);


        System.out.println(myData.get(0).isFull());
        System.out.println(myData.get(0).isUrlShareable());
        System.out.println(JSONObject.toJSON(new MyData()));

        System.out.println(new MyData().isFull());

    }

    @Test
    public void testJsonToObject1() {

        System.out.println(contains("", 123));
    }

    @Test
    public void testJsonToObjectFeild() {
        String json = "{ \"_id\" : \"5a757d42420ecf4711c6811e\", \"content\" : { \"id\" : 2650899860319638593, \"text\" : \"耽美广播剧\" }, \"fromUser\" : { \"id\" : 2531992760048922668, \"name\" : \"『重生』我不是我自己\" }, \"type\" : 27, \"createTime\" : 1517649218522, \"checkRubbish\" : true, \"status\" : 2, \"extinfo\" : { \"filterStatus\" : 2, \"hitKeyWord\" : { \"hitKeyWord\" : [\"耽美\", \"广播剧\"] }, \"rcode\" : 0 }, \"reviewUser\" : { \"id\" : 23, \"name\" : \"wuyujie\" }, \"modifyTime\" : 1517819912812 }";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("content"));
        System.out.println(((JSONObject) jsonObject.get("fromUser")).get("id"));


        System.out.println(jsonObject.get("type"));
        System.out.println("27".equals(jsonObject.get("type").toString()));

        Content content = JSON.parseObject(jsonObject.get("content").toString(), Content.class);
        System.out.println(content);

        System.out.println(JSON.parseObject(json, MyMsg.class));
    }

    public boolean contains(String data, Object target) {
        if (target == null) {
            return false;
        }
        String temp = data + ",";
        return temp.contains(target.toString() + ",");
    }


    @Test
    public void testA() {
        String password = "biubiu biu ";
        String substring = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString()
                .substring(8, 24);
        System.out.println(substring);
    }

    @Test
    public void testMap() {
        MyData data = new MyData();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("round1", 1);
        map.put("round2", 2);
        map.put("round3", 3);

        data.setMap(map);
        System.out.println(JSONObject.toJSON(data));
        System.out.println(JSONObject.toJSON(map));
    }

    @Test
    public void testObject() {
        CategoryVo s = new CategoryVo();
        System.out.println(JSONObject.toJSON(s));
    }


    @Test
    public void testObjectA() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);

        System.out.println(year);

        Long no = 14666L;
        String noStr = null;
        if (no >= 10000) {
            noStr = String.valueOf(no);
        } else {
            noStr = String.format("%05d", no);
        }

        System.out.println(noStr);

       String a = "LZ-CL-201800009-444";

        System.out.println(a.indexOf("-",6));
        System.out.println(a.substring(0,a.indexOf("-",6)));

        System.out.println(DateUtil.getDayAfter(new Date(),1));

        String st = "com1112222com666";
        System.out.println(st.replaceAll("com","333"));


        Date date = DateUtil.formatStrToNormalDate("2018-07-26 00:00:00");
        System.out.println(date);

        Date date1 = DateUtil.formatStrToNormalDate("2018-07-26");
        System.out.println(date1);

        String stA = "LZ-CL-201800049-1";
        System.out.println(stA.split("-")[3]);
        long twoDay = 48 * 3600 * 1000;
        System.out.println(twoDay);

    }



}
