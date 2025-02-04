package com.max.dto;

/**
 * Author: max
 * Discription: 校验验证码的结果数据
 */
public class CheckLoginDTO {

    private boolean checkStatus;

    private String desc;

    public CheckLoginDTO(boolean checkStatus, String desc) {
        this.checkStatus = checkStatus;
        this.desc = desc;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CheckLoginDTO{" +
                "checkStatus=" + checkStatus +
                ", desc='" + desc + '\'' +
                '}';
    }


}
