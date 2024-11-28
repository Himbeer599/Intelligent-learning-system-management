package com.learn.controller;

import com.learn.pojo.Dept;
import com.learn.pojo.Result;
import com.learn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    //面向接口形式进行遍历声明
    @Autowired
    private DeptService deptService;
    //一定要与接口文档路径保持一致
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("Fetch all department data");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
