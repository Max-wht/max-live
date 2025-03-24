package com.max.api.VO;

/**
 * @author Max
 * @description：用来返回用户信息
 * @date 2025/3/20 17:51
 */
public class UserVO {
    private String id;
    private String userAvatar;
    private String userName;
    private Integer userRole;
    private Integer subCount;

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                ", subCount=" + subCount +
                '}';
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }
}
