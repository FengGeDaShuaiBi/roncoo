package com.website.controller;

import com.model.pojo.Website_Link;
import com.utils.page.WebsiteLinkPage;
import com.website.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class demoController {

    @Autowired
    WebsiteService websiteService;

    @GetMapping("/demo")
    public void demo() {
        System.out.println("1");
        WebsiteLinkPage page = new WebsiteLinkPage();
        page.setLink_name("龙果学院");
        page.setStatus_id(1);
        try {
            List<Website_Link> links = websiteService.getWebsiteLinkByPage(page);
            System.out.println(links.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
