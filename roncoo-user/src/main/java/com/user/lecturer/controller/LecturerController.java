package com.user.lecturer.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.Lecturer;
import com.model.generator.pojo.LecturerAudit;
import com.model.generator.pojo.LecturerAuditExample;
import com.model.generator.pojo.LecturerExample;
import com.model.vo.CreateLecturerVo;
import com.model.vo.SelectLecturerVo;
import com.user.lecturer.service.LecturerAuditService;
import com.user.lecturer.service.LecturerService;
import com.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "LecturerController", description = "讲师接口测试")
public class LecturerController {


    @Autowired
    LecturerService lecturerService;

    @Autowired
    LecturerAuditService lecturerAuditService;

    @GetMapping("/select")
    @ApiOperation(value = "查询讲师数据", httpMethod = "GET", produces = "application/json", response = Dto.class, protocols = "HTTP",
            notes = "<p>系统出现异常：10002</p>")
    public Dto selectLecturer(SelectLecturerVo vo) {
        try {
            LecturerExample example = new LecturerExample();
            LecturerExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(vo.getStatusId()))
                criteria.andStatusIdEqualTo(vo.getStatusId());
            if (EmptyUtils.isNotEmpty(vo.getLecturer_name()))
                criteria.andLecturerNameEqualTo(vo.getLecturer_name());
            if (EmptyUtils.isNotEmpty(vo.getLecturerMobile()))
                criteria.andLecturerMobileEqualTo(vo.getLecturerMobile());

            List<Lecturer> lecturers = lecturerService.lecturers(example);
            if (EmptyUtils.isEmpty(lecturers)) {
                return DtoUtil.returnSuccess("未查询到对应的讲师信息");
            }
            return DtoUtil.returnDataSuccess(lecturers);
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增讲师数据", httpMethod = "POST", produces = "application/json", response = Dto.class, protocols = "HTTP",
            notes = "<p>系统出现异常：10002</p>")
    public Dto createLecturer(/*@ApiParam(name = "vo", value = "JSON格式", required = false) CreateLecturerVo vo*/) {
        try {
            LecturerAudit audit = new LecturerAudit();
            /*audit.setLecturerEmail(vo.getLecturerEmail());
            audit.setAuditStatus(vo.getStatusId());
            audit.setHeadImgUrl(vo.getHeadImgUrl());
            audit.setLecturerMobile(vo.getLecturerMobile());
            audit.setLecturerUserNo(vo.getLecturerUserNo());
            audit.setSort(vo.getSort());
            audit.setIntroduce(vo.getIntroduce());
            audit.setLecturerProportion(vo.getLecturerProportion());*/
            audit.setLecturerProportion(new BigDecimal("0.7000"));
            audit.setSort(1);
            audit.setLecturerUserNo((long) 2018112015051635L);
            audit.setLecturerMobile("13800138001");
            audit.setHeadImgUrl("demo");
            audit.setLecturerName("demo");
            audit.setStatusId((byte) 1);
            audit.setLecturerEmail("1811624890@qq.com");
            audit.setId((long) 9);
            audit.setGmtModified(new Date());
            audit.setGmtCreate(new Date());
            audit.setPosition("1");
            audit.setIntroduce("demo");

            int result = lecturerAuditService.insert(audit);

            if (result == 0)
                return DtoUtil.returnFail("系统出现未知异常", "10002");
            return DtoUtil.returnSuccess("新增成功");
        } catch (Exception e) {
            return DtoUtil.returnFail("系统出现异常", "10002");
        }

    }

}
