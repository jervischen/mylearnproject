package com.example.demo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpClientUtil;

import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2020-04-16 11:56
 */
public class CatFilter {

    public static void main(String[] args) {
        String projectName = "app_live_recreation";
        String queryName = "getTabRecomend";


        Set<String> allService = getAllService("app_live_recreation");
        for (String service : allService) {
            String url = "";
            url = String.format(url, service, queryName);
            String result = HttpClientUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject);
        }


    }


    public static Set<String> getAllService(String projuectName) {
        String url = "";
        projuectName = projuectName.replace("-", "_").trim();
        url = String.format(url, projuectName);

        String result = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject == null) {
        }
        //获取调用的项目
        JSONObject callProjectsInfo = jsonObject.getJSONObject("callProjectsInfo");
        Set<String> callProjectList = callProjectsInfo.keySet();
        callProjectList.remove("AllClients");

        return callProjectList;

    }


}
