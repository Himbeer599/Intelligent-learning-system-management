package com.learn.mapper;


import com.learn.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //原始分页查询语句
    //search count number
//    @Select("select count(*) from emp e left join department d on e.dept_id = d.id")
//    public Long count();
    //search all emps (includes分页)
//    @Select("select e.*, d.name deptName from emp e left join department d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

//  @Select("select e.*, d.name deptName from emp e left join department d on e.dept_id = d.id order by e.update_time desc")

    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

}
