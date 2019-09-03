package com.example.demo.mogodbUtil;

import com.mongodb.MongoClient;

/**
 * @author Chen Xiao
 * @since 2019-09-03 13:02
 */
public class MogoDBMain {
    //数据库名sc_live_amusement_task
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("172.17.6.61", 20000);


    }
}
