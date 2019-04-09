package com.example.demo.lizhibean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.lizhibean.pk.NextPkInfo;
import com.example.demo.lizhibean.pk.PkInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created in 2018-04-13 17:10.
 *
 * @author chenxiao
 */
public class LizhiTestJson {
    private static Logger logger = LoggerFactory.getLogger(LizhiTestJson.class);

    @Test
    public void testNextPk(){
        Map<String, Object> data = new HashMap<>();
        data.put("currentNjStatus", 111);
        data.put("nextPkInfo", new NextPkInfo());

        System.out.println(JSONObject.toJSON(data));
    }

    @Test
    public void testPkInfoList(){
         List<PkInfo> list = new ArrayList<>();
         list.add(new PkInfo());
        System.out.println(JSONObject.toJSON(list));
    }
}