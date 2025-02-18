package com.max.dto;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {

    private boolean loginStatus;

    private String desc;

    private Long userId;

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "loginStatus=" + loginStatus +
                ", desc='" + desc + '\'' +
                ", userId=" + userId +
                '}';
    }

    public static UserLoginDTO UserLoginSuccess(Long userId){
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setLoginStatus(true);
        userLoginDTO.setUserId(userId);
        return userLoginDTO;
    }
}
