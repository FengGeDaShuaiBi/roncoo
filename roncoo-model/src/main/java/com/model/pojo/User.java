package com.model.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User implements UserDetails {


    private Integer id;//主键
    private Date gmt_create;//创建时间
    private Date gmt_modified;//修改时间
    private Integer status_id;//状态(1:正常，0:禁用)
    private Integer sort;//排序
    private Integer user_no;//用户编号
    private String user_name;//用户名
    private Integer mobile;//手机号码
    private String mobile_psw;//登录密码
    private String remark;//备注
    private List<Role> roles;//角色列表

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

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

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getMobile_psw() {
        return mobile_psw;
    }

    public void setMobile_psw(String mobile_psw) {
        this.mobile_psw = mobile_psw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return mobile_psw;
    }

    @Override
    public String getUsername() {
        return user_name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
