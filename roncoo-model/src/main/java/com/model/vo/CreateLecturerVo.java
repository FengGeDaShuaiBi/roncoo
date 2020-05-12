package com.model.vo;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * 新增讲师VO
 */
@ApiModel(value = "CreateLecturerVo", description = "CreateLecturerVo")
public class CreateLecturerVo {

    private Byte statusId;

    private Integer sort;

    private Long lecturerUserNo;

    private String lecturerName;

    private String lecturerMobile;

    private String lecturerEmail;

    private BigDecimal lecturerProportion;

    private String headImgUrl;

    private String introduce;

    private String position;

    public Byte getStatusId() {
        return statusId;
    }

    public void setStatusId(Byte statusId) {
        this.statusId = statusId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getLecturerUserNo() {
        return lecturerUserNo;
    }

    public void setLecturerUserNo(Long lecturerUserNo) {
        this.lecturerUserNo = lecturerUserNo;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerMobile() {
        return lecturerMobile;
    }

    public void setLecturerMobile(String lecturerMobile) {
        this.lecturerMobile = lecturerMobile;
    }

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail;
    }

    public BigDecimal getLecturerProportion() {
        return lecturerProportion;
    }

    public void setLecturerProportion(BigDecimal lecturerProportion) {
        this.lecturerProportion = lecturerProportion;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
