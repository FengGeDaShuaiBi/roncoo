package com.website.service;

import com.model.dao.WebsiteMapper;
import com.model.pojo.Website_Link;
import com.utils.page.WebsiteLinkPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    WebsiteMapper websiteMapper;

    public List<Website_Link> getWebsiteLinkByPage(WebsiteLinkPage page) throws Exception {
        return websiteMapper.getWebsiteLinkByPage(page);
    }

}
