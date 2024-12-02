package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import com.learn.pojo.PageResult;
import com.learn.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        Page<Clazz> c = (Page<Clazz>) clazzList;
        return new PageResult(c.getTotal(),c.getResult());
    }
}
