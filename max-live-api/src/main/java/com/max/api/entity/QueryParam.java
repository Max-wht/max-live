package com.max.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:34
 */
public class QueryParam implements Serializable {

    //ID
    private Integer id;
    //当前页数
    private Integer current;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;


    @Override
    public String toString() {
        return "QueryParam{" +
                "id=" + id +
                ", current=" + current +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
