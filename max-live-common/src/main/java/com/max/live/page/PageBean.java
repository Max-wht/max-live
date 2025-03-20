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
    private int total;
    public PageBean(List<T> row) {
        this.row = row;
    }
    public PageBean(List<T> row, int total) {
        this.row = row;
        this.total = total;
    }

    public PageBean() {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "row=" + row +
                ", total=" + total +
                '}';
    }
}
