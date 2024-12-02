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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("info of searching employees are listed as below:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
//        System.out.println("Delete department according to id:"+ id);
        log.info("Delete employee according to id:{}"+ ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

}
