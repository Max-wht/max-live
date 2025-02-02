package com.max.common.redis;


import org.springframework.context.annotation.Configuration;

@Configuration
public class SMSCatchKeyBuilder extends RedisKeyBuilder{

    public static final String SMS_LOGIN_CODE_KEY = "sms:login:code:";
    public String buildSmsLoginCodeKey(String mobile){
        return super.getPrefix()+SMS_LOGIN_CODE_KEY+mobile;
    }
}
