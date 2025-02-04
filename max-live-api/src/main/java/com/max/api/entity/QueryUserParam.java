package com.max.api.entity;

import java.io.Serializable;

public class QueryUserParam implements Serializable {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QueryUser{" +
                "userId=" + userId +
                '}';
    }
}
