package com.course.controller;

import com.course.service.CourseService;
import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.Course;
import com.model.generator.pojo.CourseCategory;
import com.model.generator.pojo.CourseCategoryExample;
import com.model.generator.pojo.CourseExample;
import com.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "课程管理测试接口")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("sel/course")
    @ApiOperation(value = "查询课程列表")
    public Dto selCourse(@RequestParam(value = "course_name", required = false) String course_name,
                         @RequestParam(value = "status_id", required = false) Byte status_id,
                         @RequestParam(value = "is_free", required = false) Byte is_free,
                         @RequestParam(value = "is_putaway", required = false) Byte is_putaway) {
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

    @PostMapping("update/course")
    @ApiOperation(value = "修改课程")
    public Dto updCourse(@RequestBody Course course) {
        try {
            course.setGmtCreate(new Date());
            course.setGmtModified(new Date());
            CourseExample example = new CourseExample();
            CourseExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(course.getId()))
                criteria.andIdEqualTo(course.getId());
            int result = courseService.updateCourse(course, example);
            if (result == 0)
                return DtoUtil.returnFail("修改失败", "10001");
            return DtoUtil.returnSuccess("修改成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @PostMapping("create/course")
    @ApiOperation(value = "新增课程")
    public Dto createCourse(@RequestBody Course course) {
        try {
            int result = courseService.createCourse(course);
            if (result == 0)
                return DtoUtil.returnFail("新增失败", "10001");
            return DtoUtil.returnSuccess("新增成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @DeleteMapping("delete/course/{Id}")
    @ApiOperation(value = "删除课程")
    public Dto deleteCourse(@PathVariable("Id") long id) {
        try {
            int result = courseService.deleteCourse(id);
            if (result == 0)
                return DtoUtil.returnFail("删除失败", "10001");
            return DtoUtil.returnSuccess("删除成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @GetMapping("select/course/category")
    @ApiOperation(value = "查询课程分类")
    public Dto selectCourseCategory() {
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo((long) 0);
        List<CourseCategory> courseExampleList1 = courseService.getCourseCategory(example);//获取所有一级分类

        for (CourseCategory courseCategory : courseExampleList1
        ) {
            CourseCategoryExample example1 = new CourseCategoryExample();
            CourseCategoryExample.Criteria criteria1 = example.createCriteria();
            criteria1.andParentIdEqualTo((long) courseCategory.getId());
            List<CourseCategory> courseExampleList2 = courseService.getCourseCategory(example);//获取所有一级分类
            courseCategory.setCourseCategories(courseExampleList2);
        }
        return DtoUtil.returnDataSuccess(courseExampleList1);
    }


}
