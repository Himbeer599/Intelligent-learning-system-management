package com.learn.mapper;

import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzMapper {

    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);


    void updateMasterIds(@Param("empIds") List<Integer> empIds);

    Clazz getById(Integer id);

//    void updateMasterName(@Param("empNames")List<Integer> empNames);
}
