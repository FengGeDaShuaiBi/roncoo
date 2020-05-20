package com.system.adv.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.Adv;
import com.model.generator.pojo.AdvExample;
import com.system.adv.service.AdvService;
import com.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "广告测试接口")
public class AdvController {

    @Autowired
    AdvService advService;

    @GetMapping("select/adv")
    @ApiOperation(value = "查询广告列表")
    public Dto selectAdv(@RequestParam(value = "adv_title", required = false) String adv_title,
                         @RequestParam(value = "plat_show", required = false) Byte plat_show,
                         @RequestParam(value = "adv_target", required = false) String adv_target
    ) {
        try {
            AdvExample example = new AdvExample();
            AdvExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(adv_title))
                criteria.andAdvTitleLike("%" + adv_title + "%");
            if (EmptyUtils.isNotEmpty(plat_show))
                criteria.andPlatShowEqualTo(plat_show);
            if (EmptyUtils.isNotEmpty(adv_target))
                criteria.andAdvTargetEqualTo(adv_target);
            List<Adv> advList = advService.selectAdvByExample(example);
            if (EmptyUtils.isEmpty(advList))
                return DtoUtil.returnSuccess("未找到对应的广告");
            return DtoUtil.returnDataSuccess(advList);
        } catch (Exception e) {
            return DtoUtil.returnFail("系统未知错误", "10002");
        }
    }

}
