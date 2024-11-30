package com.learn.service;

import com.learn.pojo.Emp;
import com.learn.pojo.PageResult;

public interface EmpService {
    PageResult page(Integer page, Integer pageSize);
}
