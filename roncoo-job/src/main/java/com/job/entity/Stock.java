package com.job.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Stock)实体类
 *
 * @author makejava
 * @since 2020-05-20 15:07:26
 */
public class Stock implements Serializable {

    private Integer id;

    private String name;

    private Integer stock;

    private Date startTime;

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

    private Date endTime;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}