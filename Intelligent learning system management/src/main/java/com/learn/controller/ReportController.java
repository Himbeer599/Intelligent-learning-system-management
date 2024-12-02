package com.learn.controller;
import com.learn.service.ReportService;

import com.learn.pojo.JobOption;
import com.learn.pojo.Result;
import com.learn.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("The number of employees are listed as below:{}");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
}
