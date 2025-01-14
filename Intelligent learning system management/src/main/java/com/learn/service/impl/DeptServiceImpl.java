package com.learn.service.impl;

import com.learn.exception.DepDeleteException;
import com.learn.mapper.DeptMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Dept;
import com.learn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {

        int empCount = empMapper.countById(id);
        if (empCount > 0) {
            throw new DepDeleteException();
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void save(Dept dept) {
        //fill other properties(such as localtime and updatetime)
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
