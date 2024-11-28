package com.learn.mapper;

import com.learn.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * fetch all departs data
     * @return
     */
    @Select("select id, name, create_time, update_time  from department order by update_time desc")
    List<Dept> findAll();
}
