package com.learn.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer degree;
    private Integer classId;
}
