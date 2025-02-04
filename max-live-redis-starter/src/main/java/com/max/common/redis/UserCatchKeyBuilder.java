package com.max.common.redis;

import org.springframework.context.annotation.Configuration;

/**
 * @author Max
 * @description
 * @date 2025/2/4 18:38
 */
@Configuration
public class UserCatchKeyBuilder extends RedisKeyBuilder {

    public static final String USER_PHONE_LOGIN_KEY = "user:phone:login:";

    public String buildUserPhoneLoginKey(String mobile) {
        return super.getPrefix() +USER_PHONE_LOGIN_KEY + mobile;
    }
}
