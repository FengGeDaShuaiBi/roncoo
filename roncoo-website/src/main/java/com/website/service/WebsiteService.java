package com.website.service;

import com.model.generator.mapper.WebsiteLinkMapper;
import com.model.generator.mapper.WebsiteNavMapper;
import com.model.generator.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    WebsiteLinkMapper websiteLinkMapper;

    @Autowired
    WebsiteNavMapper websiteNavMapper;

    public List<WebsiteLink> getWebsiteLinkByPage(WebsiteLinkExample example) throws Exception {
        return websiteLinkMapper.selectByExample(example);
    }

    public List<WebsiteNav> getWebsiteNavByPage(WebsiteNavExample example) throws Exception {
        return websiteNavMapper.selectByExample(example);
    }


}
