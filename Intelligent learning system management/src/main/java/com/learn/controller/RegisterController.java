//package com.learn.controller;
//
//import com.learn.pojo.Emp;
//import com.learn.pojo.RegisterInfo;
//import com.learn.pojo.Result;
//import com.learn.service.EmpService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//public class RegisterController {
//
//    @Autowired
//    private EmpService empService;
//
//    @PostMapping("/register")
//    public Result register(@RequestBody Emp emp) {
//
//        RegisterInfo registerInfo = empService.register(emp);
//        if(registerInfo == null){
//            return Result.error("The user has been registered! Please try another username");
//        }
//        return Result.success();
//    }
//
//}
