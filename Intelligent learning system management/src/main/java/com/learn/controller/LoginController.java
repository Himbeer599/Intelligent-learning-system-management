package com.learn.controller;

import com.learn.pojo.Emp;
import com.learn.pojo.LoginInfo;
import com.learn.pojo.Result;
import com.learn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {

        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("Wrong password or username");
    }

}
