package com.model.pojo;

import java.util.Date;

public class Lecturer {

    private long id;//主键
    private Date gmt_create;//创建时间
    private Date gmt_modified;//修改时间
    private Integer status_id;//状态(1:正常，0:禁用)
    private Integer sort;//排序

    private long lecturer_user_no;//讲师用户编号
    private String lecturer_name;//讲师名称
    private String lecturer_mobile;//讲师手机
    private String lecturer_email;//讲师用户邮箱
    private String position;//职位
    private String head_img_url;//头像
    private String introduce;//简介
    private float lecturer_proportion;//讲师分成比例

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getLecturer_user_no() {
        return lecturer_user_no;
    }

    public void setLecturer_user_no(long lecturer_user_no) {
        this.lecturer_user_no = lecturer_user_no;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getLecturer_mobile() {
        return lecturer_mobile;
    }

    public void setLecturer_mobile(String lecturer_mobile) {
        this.lecturer_mobile = lecturer_mobile;
    }

    public String getLecturer_email() {
        return lecturer_email;
    }

    public void setLecturer_email(String lecturer_email) {
        this.lecturer_email = lecturer_email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public float getLecturer_proportion() {
        return lecturer_proportion;
    }

    public void setLecturer_proportion(float lecturer_proportion) {
        this.lecturer_proportion = lecturer_proportion;
    }
}
