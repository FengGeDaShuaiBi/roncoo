package com.website.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.WebsiteLink;
import com.model.generator.pojo.WebsiteLinkExample;
import com.model.generator.pojo.WebsiteNav;
import com.model.generator.pojo.WebsiteNavExample;
import com.utils.EmptyUtils;
import com.website.service.WebsiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "站点导航", description = "站点导航")
public class WebsiteNavController {

    @Autowired
    WebsiteService websiteService;

    @GetMapping("/website/selnav")
    @ApiOperation(value = "查询站点导航", httpMethod = "GET", produces = "application/json", response = Dto.class, protocols = "HTTP",
            notes = "<p>系统出现异常：10002</p>")
    public Dto selNav(@RequestParam("nav_name") String nav_name, @RequestParam("status_id") Byte status_id) {
        try {
            WebsiteNavExample example = new WebsiteNavExample();
            WebsiteNavExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(nav_name))
                criteria.andNavNameLike("%" + nav_name + "%");
            if (EmptyUtils.isNotEmpty(status_id))
                criteria.andStatusIdEqualTo(status_id);
            List<WebsiteNav> navList = websiteService.getWebsiteNavByPage(example);
            if (EmptyUtils.isEmpty(navList))
                return DtoUtil.returnSuccess("未查询到");
            return DtoUtil.returnDataSuccess(navList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统出现未知错误", "10002");
        }
    }





    @GetMapping("/website/sellink")
    @ApiOperation(value = "查询站点友情链接", httpMethod = "GET", produces = "application/json", response = Dto.class, protocols = "HTTP",
            notes = "<p>系统出现异常：10002</p>")
    public Dto selLink(@RequestParam("link_name") String link_name, @RequestParam("status_id") Byte status_id) {
        try {
            WebsiteLinkExample example = new WebsiteLinkExample();
            WebsiteLinkExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.isNotEmpty(link_name))
                criteria.andLinkNameLike("%" + link_name + "%");
            if (EmptyUtils.isNotEmpty(status_id))
                criteria.andStatusIdEqualTo(status_id);
            List<WebsiteLink> navList = websiteService.getWebsiteLinkByPage(example);
            if (EmptyUtils.isEmpty(navList))
                return DtoUtil.returnSuccess("未查询到");
            return DtoUtil.returnDataSuccess(navList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统出现未知错误", "10002");
        }
    }

}
