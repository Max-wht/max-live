package com.max.live.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/10 19:03
 */
public class PageBean<T> implements Serializable {

    private List<T> row;

    public PageBean(List<T> row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "row=" + row +
                '}';
    }

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }
}
