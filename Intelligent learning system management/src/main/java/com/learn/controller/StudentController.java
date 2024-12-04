package com.learn.controller;

import com.learn.pojo.*;
import com.learn.service.ClazzService;
import com.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("Fetch student by id:"+ id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("update student:{}"+ student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("Delete student according to ids:{}"+ ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }
}
