package com.max.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Max
 * @description
 * @date 2025/3/27 17:24
 */
public class ProjectDTO implements Serializable {

    private Integer id; // 项目表主键id
    private String name; // 云项目名称
    private String pDescription; // 云项目简介
    private String categoryId; // 云项目的类别Id
    private Date createTime; // 项目上线的日期
    private String avatar; // 简介图片
    private Integer managerId; // 任务管理员的用户ID

    // Getter and Setter methods for the properties

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPDescription() {
        return pDescription;
    }

    public void setPDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}