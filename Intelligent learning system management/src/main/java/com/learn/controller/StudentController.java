package com.learn.controller;

import com.learn.pojo.*;
import com.learn.service.ClazzService;
import com.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/students")
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page (StudentQueryParam studentQueryParam) {
        System.out.println("@@@@"+studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result create(@RequestBody Student student) {
        studentService.creatStudent(student);
        return Result.success();
    }
//    /*PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
//        System.out.println(pageResult.getTotal()); // 获取总记录数
//        for (Clazz clazz : pageResult.getRows()) { // 遍历当前页的班级数据
//                System.out.println(clazz.getName());   // 输出班级名称
//    }*/
//
//    @GetMapping("/{id}")
//    public Result getById(@PathVariable Integer id){
//        log.info("Fetch clazz by id:"+ id);
//        Clazz clazz = clazzService.getInfo(id);
//        return Result.success(clazz);
//    }
//    @PutMapping
//    public Result update(@RequestBody Clazz clazz){
//        log.info("update clazz:{}"+ clazz);
//        clazzService.update(clazz);
//        return Result.success();
//    }
//
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Integer id){
//        log.info("Delete clazz according to id:{}"+ id);
//        clazzService.deleteById(id);
//        return Result.success();
//    }
}
