package com.max.dto;

import java.io.Serializable;

/**
 * @author Max
 * @description
 * @date 2025/3/27 17:01
 */
public class CategoryDTO implements Serializable {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "CatagoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

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
