package com.example.demo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.excel.ReadExcelUtil;
import com.example.demo.util.HttpClientUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2020-04-08 10:10
 */
public class ServerCatUml {

    static List<String> projecdNameList = ReadExcelUtil.readExcel(2);
//    static List<String> projecdNameList = new ArrayList<>();

    static Set<String> dependAllProjectList = new HashSet<>();

    static List<String> excludeProjectList = ReadExcelUtil.readExcel(1);
    static List<String> includeProjectList = ReadExcelUtil.readExcel(2);
    static List<String> printProjextList = Lists.newArrayList();

    static int forIndex = 1;

//    static String prefix = "";

    @Test
    public void get(){
        List<String> all = ReadExcelUtil.readExcel(3);
        List<String> comfir = ReadExcelUtil.readExcel(4);
        for (String one : all) {
            if (!comfir.contains(one.trim())) {
                System.out.println(one);
            }
        }
    }

    public static void main(String[] args) {

        excludeProjectList.clear();
        projecdNameList.clear();
        projecdNameList.add("lz_live_oss_online");
        projecdNameList.add("lz_live_oss_online");
        projecdNameList.add("lz_live_oss_online");
        projecdNameList.add("lz_live_oss_online");
        for (String s : projecdNameList) {
            List<String> list = Lists.newArrayList();
            list.add(s);
            System.out.println("=======================");
            allProject(list,1);
        }
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
        excludeProjectList.clear();
    }

    public static void allProject(List<String> projecdNameList,int count) {
        String prefix ="";
        for (int i=0;i< count;i++){
            prefix += "*";
        }
        if (count >3){
            --forIndex;
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
            ++forIndex;
            excludeProjectList.add(project);
//            if (!includeProjectList.contains(project)){
//                continue;
//            }
            String url = "";
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
//            callProjectList.remove("data_center_proxy");
//
//            if (!printProjextList.contains(project)){
//                System.out.println(prefix + " " + project);
//                printProjextList.add(project);
//            }
                System.out.println(prefix + " " + project);
//            System.out.println("项目名：" + project + " 调用了" + callProjectList);
            if (!callProjectList.isEmpty()) {
                dependAllProjectList.addAll(callProjectList);
                for (String pro : callProjectsInfo.keySet()) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(pro);
                    if (forIndex > 3){
                        break;
                    }
                    allProject(list,forIndex);
                }
            }
        }
        --forIndex;
//        System.out.println("所有项目：");
//        System.out.println(dependAllProjectList);
    }

    @Test
    public void b(){
        List<String> contractIds = Lists.newArrayListWithCapacity(0);
        System.out.println(contractIds);

        Long a = 123L;
        long b = 123;
        System.out.println(a==b);
    }

}
