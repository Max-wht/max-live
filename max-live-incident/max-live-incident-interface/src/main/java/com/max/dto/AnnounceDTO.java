package com.max.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:36
 */
public class AnnounceDTO extends QueryDTO implements Serializable {
    //标题
    private String name;
    //内容
    private String content;
    private Date createTime;

    @Override
    public String toString() {
        return "AnnounceDTO{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
