package com.system.adv.service;

import com.model.generator.mapper.AdvMapper;
import com.model.generator.pojo.Adv;
import com.model.generator.pojo.AdvExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvService {

    @Autowired
    AdvMapper advMapper;

    public List<Adv> selectAdvByExample(AdvExample example) {
        return advMapper.selectByExample(example);
    }

}
