package com.model.dao;

import com.model.generator.pojo.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<SysMenu> getMenuAll();

    int update(String course_name, long id);
}
