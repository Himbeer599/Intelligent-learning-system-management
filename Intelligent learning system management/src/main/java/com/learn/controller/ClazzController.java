package com.learn.controller;

import com.learn.pojo.*;
import com.learn.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/clazzs")
@RestController
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("info of searching classes are listed as below:{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        log.info("info of lists after search:{}", pageResult.getRows());
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        clazzService.creatClazz(clazz);
//        log.info("info of classes after search:{}", clazz);
        return Result.success();
    }

}
