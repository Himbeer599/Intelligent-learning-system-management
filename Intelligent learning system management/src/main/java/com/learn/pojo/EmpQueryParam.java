package com.learn.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate begin;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate end;
}
