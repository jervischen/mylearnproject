package com.example.lizhi;

import lombok.Data;

/**
 * Created in 2019-07-04 20:07.
 *
 * @author chenxiao
 */
@Data
public class UtilsConf {
    /**
     * 命名空间
     */
    public static final String NAMESPACE = "lz_pp_common_utils";

    /**
     * 服务器id
     */
    private int serverId;

    /**
     * dc配置key，默认不改
     */
    private String dcProxyConfigKey = "dc_proxy_office" ;

    /**
     * dc连接数
     */
    private int dcProxyConnectionCount = 10;

    /**
     * pp 对应appId
     */
    private long ppAppId = 10919088;

    private String test = "";

    /**
     * pp分离：
     *
     * 测试用户白名单，因为逗号隔开
     */
    private String testWhiteUserPpSep;

    /**
     * pp分离：
     *
     * 测试手机号码白名单，因为逗号隔开
     */
    private String testWhitePhonePpSep;

    /**
     * pp分离：
     *
     * 测试支付id白名单，因为逗号隔开
     */
    private String testWhitePayIdPpSep;

    /**
     * pp分离：
     *
     * 提供给外部使用
     */
    private boolean switchCommonPpSep = true;

    /**
     * pp分离：
     *
     * pp自己开关
     */
    private boolean switchPpCommonPpSep = true;
}
