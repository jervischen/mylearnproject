package com.example.demo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.example.demo.excel.ReadExcelUtil;
import com.example.demo.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2020-04-08 10:10
 */
public class ServerCatUml {

    static List<String> projecdNameList = ReadExcelUtil.readExcel(0);
//    static List<String> projecdNameList = new ArrayList<>();

    static Set<String> dependAllProjectList = new HashSet<>();

    static List<String> excludeProjectList = ReadExcelUtil.readExcel(1);
    static List<String> includeProjectList = ReadExcelUtil.readExcel(2);
    static List<String> printProjextList = Lists.newArrayList();

//    static String prefix = "";

    public static void main(String[] args) {
        projecdNameList.clear();
        excludePro();
        projecdNameList.add("lz_live_amusement");
        excludeProjectList.clear();
        allProject(projecdNameList,4);
//        System.out.println("所有项目：" + dependAllProjectList);
    }

    public static void excludePro(){
        excludeProjectList.add("excludeProjectList");
        excludeProjectList.add("lz_sns_voicefriend_gift");
        excludeProjectList.add("lz_live_aggregation_common");
        excludeProjectList.add("lz_live_middle_prop");
        excludeProjectList.add("lz_live_aggregation_common_pre");
        excludeProjectList.add("lz_rec_live_revenue_lucky_bag");
        excludeProjectList.add("lz_rec_search_relation_id");
        excludeProjectList.add("unknown");
        excludeProjectList.add("lz_live_kylin_home");
        excludeProjectList.add("lz_content_review_provider_online");
        excludeProjectList.add("lz_live_aggregation_common");
        excludeProjectList.add("ps_lz_trade_tradinggift");
        excludeProjectList.add("lz_vod_verify");
        excludeProjectList.add("lz_datacenter_chat_online");
        excludeProjectList.add("ps_lz_trade_profitsharing");
        excludeProjectList.add("lz_payment_tenantcenter");
        excludeProjectList.add("lz_common_risk_account_list");
        excludeProjectList.add("lz_account_security");
        excludeProjectList.add("lz_common_commservice");
        excludeProjectList.add("lz_common_risk_strategy");
        excludeProjectList.add("lz_push_appproxy_online");
        excludeProjectList.add("app_online");
        excludeProjectList.add("lz_sms");
        excludeProjectList.add("lz_playsheet");
        excludeProjectList.add("lz_vod_trade");
        excludeProjectList.add("lz_sns_relation");
        excludeProjectList.add("lz_common_risk_strategy");
        excludeProjectList.add("ps_lz_trade_stock");
        excludeProjectList.add("lz_sns_relation");
        excludeProjectList.add("lz_common_pay_pixiu");
        excludeProjectList.add("lz_rec_recommend_platform_model_live");
        excludeProjectList.add("lz_rec_live_extra_attribute");
        excludeProjectList.add("lz_growth_adfilter");
        excludeProjectList.add("lz_common_trade_kylin");
        excludeProjectList.add("lz_rec_recommend_platform_model_live");
        excludeProjectList.add("lz_trade_coupon");
        excludeProjectList.add("lz_rec_recommend_platform_model_live");
        excludeProjectList.add("lz_live_fanslevel");
        excludeProjectList.add("lz_live_net_signing");
        excludeProjectList.add("lz_live_rcmd_distribute");
        excludeProjectList.add("lz_rec_internal_search");
        excludeProjectList.add("lz_payment");
        excludeProjectList.add("lz_vod_label");
        excludeProjectList.add("lz_trend");
        excludeProjectList.add("lz_common_usercenter");
        excludeProjectList.add("lz_live_wolf");
        excludeProjectList.add("lz_datacenter_chat_online");
        excludeProjectList.add("lz_vod_business");
    }

    public static void allProject(List<String> projecdNameList,int count) {
        String prefix ="";
        for (int i=0;i< count;i++){
            prefix += "*";
        }
        if (count > 10){
            return;
        }
//        System.out.println(count);
//        System.out.println("移除前：" + projecdNameList);
        Set<String> callProjectList = new HashSet<>();
        //移除已经有得项目
//        projecdNameList.removeAll(new ArrayList<>(excludeProjectList));

//        System.out.println("需要移除的：" + excludeProjectList);
//        System.out.println("移除后：" + projecdNameList);
        for (String project : projecdNameList) {
            excludeProjectList.add(project);
//            if (!includeProjectList.contains(project)){
//                continue;
//            }
            String url = "http://ymcat_monitor_web.lizhi.fm/cat/r/cross?op=api&domain=%s&ip=All&date=20200406&reportType=week";
            project = project.replace("-", "_").trim();
            url = String.format(url, project);

            String result = HttpClientUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject == null) {
                //排除空项目
                System.out.println("没有加入cat的项目：" + project);
                continue;
            }
            //获取调用的项目
            JSONObject callProjectsInfo = jsonObject.getJSONObject("callProjectsInfo");
            callProjectList = callProjectsInfo.keySet();
            callProjectList.remove("AllServers");
            callProjectList.remove("data_center_proxy");

            if (!printProjextList.contains(project)){
                System.out.println(prefix + " " + project);
                printProjextList.add(project);
            }
//            System.out.println("项目名：" + project + " 调用了" + callProjectList);
            if (!callProjectList.isEmpty()) {
                dependAllProjectList.addAll(callProjectList);
//                allProject(new ArrayList<>(callProjectList),++count);

                for (String pro : callProjectsInfo.keySet()) {
//                    if (!excludeProjectList.contains(pro)){
//                    }
                    System.out.println(prefix + "* " + pro);
                }
            }


        }

//        System.out.println("所有项目：");
//        System.out.println(dependAllProjectList);
    }


}
