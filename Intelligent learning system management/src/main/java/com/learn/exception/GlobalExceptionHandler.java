package com.learn.exception;

import com.learn.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
        public Result handleException(Exception e) {
            e.printStackTrace();
        return Result.error("Sorry, the operation failed. Please contact the administrator.");
        }
}
