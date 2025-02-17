package com.max.user.provider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Max
 * @description
 * @date 2025/2/17 21:16
 */

@Configuration
@ConfigurationProperties(prefix = "maxlive.sms.ccp")
public class SMSCCPConfig {

    private String ServerIP;
    private String Port;
    private String accountSid;
    private String accountToken;
    private String appID;
    private String templateID;
    private String testPhone;
    private boolean test;

    public boolean getTest() {
        return test;
    }
    public void setTest(boolean test) {
        this.test = test;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getServerIP() {
        return ServerIP;
    }

    public void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getTestPhone() {
        return testPhone;
    }

    public void setTestPhone(String testPhone) {
        this.testPhone = testPhone;
    }
}
