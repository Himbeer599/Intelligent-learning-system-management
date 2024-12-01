package com.learn.controller;

import com.learn.pojo.Emp;
import com.learn.pojo.EmpQueryParam;
import com.learn.pojo.PageResult;
import com.learn.pojo.Result;
import com.learn.service.EmpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/emps")
@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * pagination
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("info of searching employees are listed as below:{}", empQueryParam);

        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }


}
