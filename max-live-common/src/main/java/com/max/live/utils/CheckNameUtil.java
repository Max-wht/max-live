package com.max.live.utils;

import org.springframework.context.annotation.Configuration;

/**
 * @author Max
 * @description
 * @date 2025/2/20 19:03
 */

public class CheckNameUtil {

    public  boolean checkWhetherBinOrHex(String originalFileName) {
        return originalFileName.toLowerCase().endsWith(".bin") || originalFileName.toLowerCase().endsWith(".hex");
    }

}
