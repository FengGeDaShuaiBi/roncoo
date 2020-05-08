package com.model.pojo;

import java.util.Date;

public class Role {

    private Integer id;//主键
    private Date gmt_create;//创建时间
    private Date gmt_modified;//修改时间
    private Integer status_id;//状态(1:正常，0:禁用)
    private Integer sort;//排序
    private String role_name;//角色名称
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
