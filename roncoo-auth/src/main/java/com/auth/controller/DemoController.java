package com.auth.controller;

import com.auth.service.UserService;
import com.model.generator.pojo.LecturerAudit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@Api(value = "DemoController", description = "测试接口")
public class DemoController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping("/order/orderInfo")
    public String demo() {
        /*Adv adv = new Adv();
        adv.setGmtCreate(new Date());
        adv.setGmtModified(new Date());
        adv.setStatusId((byte) 1);
        adv.setSort(1);
        adv.setAdvTitle("demo");
        adv.setAdvImg("demo");
        adv.setAdvUrl("demo");
        adv.setAdvTarget("demo");
        adv.setAdvLocation((byte) 1);
        adv.setBeginTime(new Date());
        adv.setEndTime(new Date());
        adv.setPlatShow((byte) 1);
        userService.insert(adv);*/
       /* AdvExample advExample = new AdvExample();
        List<Adv> list = userService.se(advExample);
        System.out.println(list.size());*/
        /*LecturerAudit audit = new LecturerAudit();
        audit.setLecturerProportion(new BigDecimal("0.7000"));
        audit.setSort(1);
        audit.setLecturerUserNo((long) 2018112015051635L);
        audit.setLecturerMobile("13800138001");
        audit.setHeadImgUrl("demo");
        audit.setLecturerName("demo");
        audit.setStatusId((byte) 1);
        audit.setLecturerEmail("1811624890@qq.com");
        audit.setId((long) 11);
        audit.setGmtModified(new Date());
        audit.setGmtCreate(new Date());
        audit.setPosition("1");
        audit.setIntroduce("demo");

        int result = lecturerAuditService.insert(audit);
        System.out.println(result);*/
        return "in";
    }

}
