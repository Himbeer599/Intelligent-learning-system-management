package com.learn.service;

import com.learn.pojo.*;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void creatStudent(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void deleteByIds(List<Integer> ids);
}
