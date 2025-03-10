package com.max.api.entity;


/**
 * @author Max
 * @description
 * @date 2025/3/10 18:36
 */
public class AnnounceParam extends QueryParam {
    //标题名字
    private String name;
    //内容
    private String content;

    @Override
    public String toString() {
        return "AnnounceParam{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
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
}
