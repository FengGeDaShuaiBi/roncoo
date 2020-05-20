package com.model.dao;

import com.model.pojo.Role;
import com.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface User1Mapper {

    public User loadUserByUsername(@Param("username") String username);

    public List<Role> getRoleByUserId(@Param("id") long id);

    public int updateUserPassword(@Param("id") long id, @Param("mobile_psw") String mobile_psw);

}
