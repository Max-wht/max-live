package com.max.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

    private Long id;
    private String nickName;
    private String avatar;
    private  String trueName;
    private Integer sex;
    private Date createTime;
    private Date updateTime;
    private int subCount;
    private int userCharacter;
    private int userLogin;
    private int classesId;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", trueName='" + trueName + '\'' +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", subCount=" + subCount +
                ", userCharacter=" + userCharacter +
                ", userLogin=" + userLogin +
                ", classesId=" + classesId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public int getUserCharacter() {
        return userCharacter;
    }

    public void setUserCharacter(int userCharacter) {
        this.userCharacter = userCharacter;
    }

    public int getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(int userLogin) {
        this.userLogin = userLogin;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }
}
