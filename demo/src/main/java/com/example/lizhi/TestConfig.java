package com.example.lizhi;

import fm.lizhi.commons.config.service.ConfigService;

/**
 * @author Chen Xiao
 * @since 2020-06-03 10:47
 */
public class TestConfig {

    public static void main(String[] args) {
        UtilsConf utilsConf = ConfigService.loadConfig(UtilsConf.class, UtilsConf.NAMESPACE);
        System.out.println(utilsConf.getTest());


    }
}
