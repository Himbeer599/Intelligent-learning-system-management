package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.EmpExprMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Emp;
import com.learn.pojo.EmpQueryParam;
import com.learn.pojo.PageResult;
import com.learn.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
    @Override
    public PageResult page(Integer page, Integer pageSize){
         * 原始分页查询
        //获取总记录数
        Long total = empMapper.count();
        //获取列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.list(start,pageSize);
        //封装结果
        return new PageResult(total,empList);*/

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());
    }

}
