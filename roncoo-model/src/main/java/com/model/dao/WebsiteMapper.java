package com.model.dao;

import com.model.pojo.Website_Link;
import com.utils.page.WebsiteLinkPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteMapper {

    public List<Website_Link> getWebsiteLinkByPage(WebsiteLinkPage page);

}
