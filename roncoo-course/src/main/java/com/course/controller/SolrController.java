package com.course.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import io.swagger.annotations.Api;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Api("solr测试")
public class SolrController {

    @Autowired
    private SolrClient client;

    @GetMapping("/select/search")
    public Dto search(@RequestParam(value = "course_name", required = true) String course_name) {
        SolrQuery query = new SolrQuery();
        //query.setQuery("course_name:" + course_name);
        QueryResponse queryResponse = null;
        try {
            queryResponse = client.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SolrDocumentList results = queryResponse.getResults();
        return DtoUtil.returnDataSuccess(results);
    }

}
