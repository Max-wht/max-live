package com.max.dto;

import java.io.Serializable;

/**
 * @author Max
 * @description
 * @date 2025/4/1 16:01
 */
public class ClassDTO implements Serializable {
    private Integer id;
    private String name;

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
}
