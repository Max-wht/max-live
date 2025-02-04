package com.max.live;

/**
 * @author Max
 * @description
 * @date 2025/2/4 18:53
 */
public enum CommonStatusEnum {
    INVALID_STATUS(0, "无效"),
    VALID_STATUS(1, "有效"),
    ;

    private Integer code;
    private String desc;

    CommonStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
