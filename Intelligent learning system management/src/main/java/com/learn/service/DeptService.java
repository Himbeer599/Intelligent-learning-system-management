package com.learn.service;

import com.learn.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * fetch all departs data
     * @return
     */
    List<Dept> findAll();
}
