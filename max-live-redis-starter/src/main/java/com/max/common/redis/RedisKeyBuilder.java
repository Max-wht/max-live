package com.max.common.redis;

import org.springframework.beans.factory.annotation.Value;

public class RedisKeyBuilder {

    @Value("${spring.application.name}")
    private String applicationName;

    private static final String SPLIT_ITEM =":";

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPrefix() {
        return applicationName + SPLIT_ITEM;
    }
}
