package com.example.demo.crawler;

import org.slf4j.Logger;

import java.io.InputStream;

/**
 * @author Chen Xiao
 * @since 2020-04-17 14:49
 */
public class ServerConfig {

    private static final String CONFIG_FILE = "/config/server.properties";

    /* ======================MONGODB配置======================= */
    /** Mongo服务器地址、端口及Collection名 */
    public static String MONGO_HOST;
    public static int MONGO_PORT;
    public static String MONGO_DB_NAME;

    /* ======================MONGODB配置======================= */
    public static void main(String[] args) {
        load();
    }

    private static void load() {
        try {
            // String conf = findConfFile();
            ConfigProperties settings = new ConfigProperties();
            InputStream is = ServerConfig.class.getResourceAsStream(CONFIG_FILE);
            settings.load(is, "UTF-8");
            is.close();

            MONGO_HOST = settings.getProperty("mongo_host");
            MONGO_PORT = Integer.parseInt(settings.getProperty("mongo_port"));
            MONGO_DB_NAME = settings.getProperty("mongo_db_name");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
