package com.user.lecturer.service;

import com.model.generator.mapper.LecturerExtMapper;
import com.model.generator.mapper.LecturerMapper;
import com.model.generator.pojo.Lecturer;
import com.model.generator.pojo.LecturerExample;
import com.model.generator.pojo.LecturerExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    @Autowired
    LecturerMapper lecturerMapper;

    @Autowired
    LecturerExtMapper lecturerExtMapper;

    public List<Lecturer> lecturers(LecturerExample example) {
        return lecturerMapper.selectByExample(example);
    }

    public int insert(Lecturer lecturer) {
        return lecturerMapper.insert(lecturer);
    }

    public Lecturer getLecturerById(long id) {
        return lecturerMapper.selectByPrimaryKey(id);
    }

    public LecturerExt getLecturerExtById(long id) {
        return lecturerExtMapper.selectByPrimaryKey(id);
    }

}
