package com.max.dto;

import java.io.Serializable;

/**
 * @author Max
 * @description
 * @date 2025/2/23 14:50
 */
public class CheckSendBinDTO implements Serializable {

    private boolean status;

    private String desc;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CheckSendBinDTO(boolean status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
