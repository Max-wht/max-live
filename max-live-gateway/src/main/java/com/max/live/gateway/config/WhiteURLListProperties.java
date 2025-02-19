package com.max.live.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/2/19 16:23
 */

@Configuration
@ConfigurationProperties(prefix = "max-live.gateway")
public class WhiteURLListProperties {

    private List<String> whiteUrlList;

    public List<String> getWhiteUrlList() {
        return whiteUrlList;
    }

    public void setWhiteUrlList(List<String> whiteUrlList) {
        this.whiteUrlList = whiteUrlList;
    }
}
