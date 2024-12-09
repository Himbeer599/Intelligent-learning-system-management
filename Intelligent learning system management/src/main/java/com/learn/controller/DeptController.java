package com.learn.controller;

import com.learn.pojo.Dept;
import com.learn.pojo.Result;
import com.learn.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //面向接口形式进行遍历声明
    @Autowired
    private DeptService deptService;
    //一定要与接口文档路径保持一致
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("Fetch all department data");
        log.info("Fetch all department data");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
//        System.out.println("Delete department according to id:"+ id);
        log.info("Delete department according to id:"+ id);
        deptService.deleteById(id);
        return Result.success();
    }
    //add a new department
    @PostMapping
    public Result save(@RequestBody Dept dept){
//        System.out.println("new department, dept=" + dept);
        log.info("Save new department, dept=" + dept);
        deptService.save(dept);
        return Result.success();
    }
    //revise a department (step1 query by Id from frontend, step2, revise(similar to add a new department)
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
//        System.out.println("Fetch department by id:"+ id);
        log.info("Fetch department by id:"+ id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("update department, dept=" + dept);
        log.info("update department, dept=" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
