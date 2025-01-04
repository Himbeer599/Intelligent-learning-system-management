package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpId {
    private Integer id; //ID,主键

    private String name; //姓名

}
