package com.learn.mapper;


import com.learn.pojo.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @Update("update emp set password = #{newpassword} where id = #{id}")
    void updatePassword(EmpPassword empPassword);


    boolean checkPassword(@Param("id") Integer id, @Param("password") String password);



    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();
    @MapKey("name")
    List<Map> countEmpGenderData();

    @Select("select e.name, e.id from emp e")
    List<EmpId> getAll();

    @Select("select count(*) from emp e where e.dept_id = #{id} ")
    int countById(@Param("id") Integer id);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameandpassword(Emp emp);
}
