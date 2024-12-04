package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.StudentMapper;
import com.learn.pojo.*;
import com.learn.service.ClazzService;
import com.learn.service.EmpLogService;
import com.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {

        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}