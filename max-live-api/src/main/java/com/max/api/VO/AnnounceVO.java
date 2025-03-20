package com.max.api.VO;
import java.util.Date;

/**
 * @author Max
 * @description
 * @date 2025/3/20 17:51
 */
public class AnnounceVO {
    private Integer id;
    private String name;
    private String content;
    private Date createTime;

    @Override
    public String toString() {
        return "AnnounceVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
