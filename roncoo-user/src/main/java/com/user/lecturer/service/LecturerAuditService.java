package com.user.lecturer.service;

import com.model.generator.mapper.LecturerAuditMapper;
import com.model.generator.pojo.LecturerAudit;
import com.model.generator.pojo.LecturerAuditExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerAuditService {

    @Autowired
    LecturerAuditMapper lecturerAuditMapper;

    public long countByExample(LecturerAuditExample example) {
        return lecturerAuditMapper.countByExample(example);
    }

    public int deleteByExample(LecturerAuditExample example) {
        return lecturerAuditMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return lecturerAuditMapper.deleteByPrimaryKey(id);
    }

    public int insert(LecturerAudit record) {
        return lecturerAuditMapper.insert(record);
    }

    public int insertSelective(LecturerAudit record) {
        return lecturerAuditMapper.insertSelective(record);
    }

    public List<LecturerAudit> selectByExample(LecturerAuditExample example) {
        return lecturerAuditMapper.selectByExample(example);
    }

    public LecturerAudit selectByPrimaryKey(Long id) {
        return lecturerAuditMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(@Param("record") LecturerAudit record, @Param("example") LecturerAuditExample example) {
        return lecturerAuditMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") LecturerAudit record, @Param("example") LecturerAuditExample example) {
        return lecturerAuditMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(LecturerAudit record) {
        return lecturerAuditMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LecturerAudit record) {
        return lecturerAuditMapper.updateByPrimaryKey(record);
    }

}
