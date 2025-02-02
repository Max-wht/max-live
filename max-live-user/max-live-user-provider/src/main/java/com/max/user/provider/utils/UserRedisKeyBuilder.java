package com.max.user.provider.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRedisKeyBuilder {

    public static final String USER_KEY = "user:key:";
    public String buildUserKey(Long userId) {
        return USER_KEY + userId;
    }
}
