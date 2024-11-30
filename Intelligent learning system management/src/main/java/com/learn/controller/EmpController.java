package com.learn.controller;

import com.learn.pojo.Emp;
import com.learn.pojo.PageResult;
import com.learn.pojo.Result;
import com.learn.service.EmpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/emps")
@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("info of searching employees are listed as below:{},{}", page, pageSize);

        PageResult<Emp> pageResult = empService.page(page,pageSize);
        return Result.success(pageResult);

    }
}
