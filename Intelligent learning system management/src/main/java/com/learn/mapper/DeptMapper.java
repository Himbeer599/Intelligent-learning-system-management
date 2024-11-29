package com.learn.mapper;

import com.learn.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * fetch all departs data
     * @return
     */
    @Select("select id, name, create_time, update_time  from department order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from department where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into department(name,create_time, update_time) values (#{name}, #{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, department.create_time, department.update_time from department where id = #{id}")
    Dept getById(Integer id);

    @Update("update department set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
