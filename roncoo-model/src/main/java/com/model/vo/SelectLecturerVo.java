package com.model.vo;

/**
 * 查询讲师AO
 */
public class SelectLecturerVo {

    private String Lecturer_name;//讲师名称
    private String lecturerMobile;//手机号
    private Byte statusId;//状态

    public String getLecturer_name() {
        return Lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        Lecturer_name = lecturer_name;
    }

    public String getLecturerMobile() {
        return lecturerMobile;
    }

    public void setLecturerMobile(String lecturerMobile) {
        this.lecturerMobile = lecturerMobile;
    }

    public Byte getStatusId() {
        return statusId;
    }

    public void setStatusId(Byte statusId) {
        this.statusId = statusId;
    }
}
