package com.learn.controller;

import com.learn.pojo.*;
import com.learn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/password")
@RestController
@Slf4j
public class PwdController {

    @Autowired
    private EmpService empService;

    /**
     * pagination
     * @return
     */


    @PutMapping
    public Result updatePassword(@RequestBody EmpPassword empPassword){
        log.info("new password info,{}"+ empPassword);
        Integer id = empPassword.getId();

        String password = empPassword.getOldpassword();
        if(empService.checkPassword(id,password)){
            empService.updatePassword(empPassword);
            return Result.success();
        }
        return Result.error("Wrong password");
    }


}
