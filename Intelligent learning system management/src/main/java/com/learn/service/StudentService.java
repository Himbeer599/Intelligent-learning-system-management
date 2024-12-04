package com.learn.service;

import com.learn.pojo.*;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);
}
