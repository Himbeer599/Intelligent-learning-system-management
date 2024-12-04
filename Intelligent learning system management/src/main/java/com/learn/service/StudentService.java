package com.learn.service;

import com.learn.pojo.*;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void creatStudent(Student student);

    Student getInfo(Integer id);

    void update(Student student);
}
