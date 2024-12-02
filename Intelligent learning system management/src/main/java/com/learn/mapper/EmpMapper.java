package com.learn.mapper;


import com.learn.pojo.Emp;
import com.learn.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

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

    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到id
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    @Update("update emp and emp_expr set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    void updateById(Emp emp);
}
