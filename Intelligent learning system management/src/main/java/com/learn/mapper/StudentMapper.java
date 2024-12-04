package com.learn.mapper;

import com.learn.pojo.Student;
import com.learn.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);
}
