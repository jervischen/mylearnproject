package com.example.http;

import com.example.demo.util.HttpClientUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2021-09-29 14:36
 */
public class HttpDemo {

    public static void main(String[] args) {
        new HttpDemo().recycleProp();
    }

    /**
     * 回收 道具
     */
    public void recycleProp() {
        ArrayList<Long> list = new ArrayList<>(50);
//        File file = new File("/Users/chenxiao/IdeaProjects/mylearnproject/demo/learn-demo/src/main/java/com/example/http/id.txt");
//        BufferedReader reader = null;
//
//
//        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            int line = 1;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }

        list.add(5204013767305677366L);

        Map<String, Object> param = new HashMap<>();
        param.put("rewardIds", list);


        String doPost = HttpClientUtil.doPost("http://ppliveh5pre.pparty.com//prop/recycleProp", param);
        System.out.println(doPost);
    }
}
