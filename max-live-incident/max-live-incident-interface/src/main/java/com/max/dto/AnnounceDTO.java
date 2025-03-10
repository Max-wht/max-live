package com.max.dto;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return "AnnounceDTO{" +
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
