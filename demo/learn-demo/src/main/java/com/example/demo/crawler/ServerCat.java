package com.example.demo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.excel.ReadExcelUtil;
import com.example.demo.util.HttpClientUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2020-04-08 10:10
 */
public class ServerCat {

    static List<String> projecdNameList = ReadExcelUtil.readExcel(1);
//    static List<String> projecdNameList = new ArrayList<>();

    static Set<String> dependAllProjectList = new HashSet<>();

    static Set<String>  excludeProjectList = new HashSet<>();
    public static void main(String[] args) {
//        projecdNameList.clear();
//        projecdNameList.add("app_live_main");
        allProject(projecdNameList);
        System.out.println("所有项目：" + dependAllProjectList);
    }


    public static void allProject(List<String> projecdNameList){
//        System.out.println("移除前：" + projecdNameList);
        Set<String> callProjectList = new HashSet<>();
        //移除已经有得项目
//        projecdNameList.removeAll(new ArrayList<>(excludeProjectList));

//        System.out.println("需要移除的：" + excludeProjectList);
//        System.out.println("移除后：" + projecdNameList);
        for (String project : projecdNameList) {
            excludeProjectList.add(project);
            String url = "";
            project = project.replace("-", "_").trim();
            url = String.format(url, project);

            String result = HttpClientUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject == null){
                //排除空项目
                System.out.println("没有加入cat的项目：" + project);
                continue;
            }
            //获取调用的项目
            JSONObject callProjectsInfo = jsonObject.getJSONObject("callProjectsInfo");
            callProjectList = callProjectsInfo.keySet();
            callProjectList.remove("AllServers");
            callProjectList.remove("data_center_proxy");


            System.out.println("项目名：" + project + " 调用了" + callProjectList);
            if (!callProjectList.isEmpty()){
                dependAllProjectList.addAll(callProjectList);
//                allProject(new ArrayList<>(callProjectList));
            }
        }

//        System.out.println("所有项目：");
//        System.out.println(dependAllProjectList);
    }



}
