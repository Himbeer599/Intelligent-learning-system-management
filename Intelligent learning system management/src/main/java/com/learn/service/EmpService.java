package com.learn.service;

import com.learn.pojo.Emp;
import com.learn.pojo.EmpQueryParam;
import com.learn.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
    PageResult page(EmpQueryParam empQueryParam);
}
