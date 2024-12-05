package com.learn.controller;

import com.learn.annotation.LogOperation;
import com.learn.pojo.*;
import com.learn.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/clazzs")
@RestController
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        //PageResult<T>， T detemines the rows of PageResult. PageResult:{total:.., rows:[{Clazz},{Clazz}..]
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result create(@RequestBody Clazz clazz) {
        clazzService.creatClazz(clazz);
        return Result.success();
    }
    /*PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        System.out.println(pageResult.getTotal()); // 获取总记录数
        for (Clazz clazz : pageResult.getRows()) { // 遍历当前页的班级数据
                System.out.println(clazz.getName());   // 输出班级名称
    }*/

    @LogOperation
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("Fetch clazz by id:"+ id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("update clazz:{}"+ clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("Delete clazz according to id:{}"+ id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAllClazz(){
        log.info("Fetch all clazzs");
        List<Clazz> clazzList = clazzService.getAll();
        return Result.success(clazzList);
    }
}
