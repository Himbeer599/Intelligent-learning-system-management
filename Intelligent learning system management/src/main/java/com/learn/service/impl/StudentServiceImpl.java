package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.EmpLogMapper;
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
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {

        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void creatStudent(Student student) {
        try {
            student.setCreateTime(LocalDateTime.now());
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.add(student);
        } finally {
            EmpLog studentLog = new EmpLog(null, LocalDateTime.now(),"info of new added student is shown below:"+student);
            empLogService.insertLog(studentLog);
        }
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }
}