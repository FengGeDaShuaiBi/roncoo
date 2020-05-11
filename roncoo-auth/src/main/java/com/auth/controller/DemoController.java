package com.auth.controller;

import com.auth.service.UserService;
import com.model.generator.mapper.AdvMapper;
import com.model.generator.pojo.Adv;
import com.model.generator.pojo.AdvExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "DemoController", description = "测试接口")
public class DemoController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping("demo")
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
        AdvExample advExample = new AdvExample();
        List<Adv> list = userService.se(advExample);
        System.out.println(list.size());
        return "in";
    }

}
