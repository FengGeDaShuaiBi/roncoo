package com.course.controller;

import com.course.service.CourseService;
import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.Course;
import com.model.generator.pojo.CourseExample;
import com.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "课程管理测试接口")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("sel/course")
    @ApiOperation(value = "查询课程列表")
    public Dto selCourse(@RequestParam(value = "course_name") String course_name,
                         @RequestParam(value = "status_id") Byte status_id,
                         @RequestParam(value = "is_free") Byte is_free,
                         @RequestParam(value = "is_putaway") Byte is_putaway) {
        try {
            CourseExample example = new CourseExample();
            CourseExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(course_name))
                criteria.andCourseNameLike("%" + course_name + "%");
            if (EmptyUtils.isNotEmpty(status_id))
                criteria.andStatusIdEqualTo(status_id);
            if (EmptyUtils.isNotEmpty(is_free))
                criteria.andIsFreeEqualTo(is_free);
            if (EmptyUtils.isNotEmpty(is_putaway))
                criteria.andIsPutawayEqualTo(is_putaway);
            List<Course> courseList = courseService.getCourseList(example);
            if (EmptyUtils.isEmpty(courseList))
                return DtoUtil.returnSuccess("未查询到对应的课程信息");
            return DtoUtil.returnDataSuccess(courseList);
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @GetMapping("upd/course")
    @ApiOperation(value = "修改课程")
    public Dto updCourse(/*@RequestBody @RequestParam("course") Course course*/) {
        try {
            Course course = new Course();
            /*course.setId((long) 1080387521456295937L);
            course.setCourseName("demo");
            course.setSort(1);
            course.setCourseLogo("demo");
            course.setLecturerUserNo((long) 123);
            course.setStatusId((byte) 1);
            course.setIsFree((byte) 1);
            course.setIsPutaway((byte) 1);
            course.setCourseSort(12);
            course.setCountStudy(12);
            course.setCountBuy(12);
            course.setPeriodTotal(12);*/
            CourseExample example = new CourseExample();
            CourseExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(course.getId()))
                criteria.andIdEqualTo(course.getId());

            int result = courseService.updateCourse(course, example);
            int a = courseService.update("demo", 1080387521456295937L);
            System.out.println(a);
            if (result == 0)
                return DtoUtil.returnFail("修改失败", "10001");
            return DtoUtil.returnSuccess("修改成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }

    }

}
