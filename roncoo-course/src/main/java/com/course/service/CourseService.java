package com.course.service;

import com.model.dao.MenuMapper;
import com.model.generator.mapper.CourseMapper;
import com.model.generator.pojo.Course;
import com.model.generator.pojo.CourseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    @Autowired
    CourseMapper courseMapper;

    @Autowired
    MenuMapper menuMapper;

    public List<Course> getCourseList(CourseExample example) {
        return courseMapper.selectByExample(example);
    }

    public int updateCourse(Course course, CourseExample example) {
        return courseMapper.updateByExampleSelective(course, example);
    }

    public int update(String name, long id) {
        return menuMapper.update(name, id);
    }

}
