package com.max.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Max
 * @description
 * @date 2025/3/24 18:47
 */
public class StudentDTO implements Serializable {
    private String avatar;
    private String nickName;
    private Integer subCount;
    private Integer userCharacter;
    private Integer classesId;
    private Date updateTime;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public Integer getUserCharacter() {
        return userCharacter;
    }

    public void setUserCharacter(Integer userCharacter) {
        this.userCharacter = userCharacter;
    }

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
