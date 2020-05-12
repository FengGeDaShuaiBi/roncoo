package com.user.lecturer.service;

import com.model.generator.mapper.LecturerMapper;
import com.model.generator.pojo.Lecturer;
import com.model.generator.pojo.LecturerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    @Autowired
    LecturerMapper lecturerMapper;

    public List<Lecturer> lecturers(LecturerExample example) {
        return lecturerMapper.selectByExample(example);
    }

    public int insert(Lecturer lecturer) {
        return lecturerMapper.insert(lecturer);
    }

}
