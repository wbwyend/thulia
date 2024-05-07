package com.wyer.server.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Function: 支付宝沙箱配置
 * Writer: wyer
 * Date: 2023/12/05 16:18
 **/
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    /**
     * 支付宝沙箱id
     */
    private String appId;
    /**
     * 支付宝沙箱私钥
     */
    private String appPrivateKey;
    /**
     * 支付宝沙箱公钥
     */
    private String aliPublicKey;
    /**
     * 反馈url
     */
    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
