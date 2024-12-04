package com.learn.mapper;

import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);


//    void updateMasterIds(@Param("empIds") List<Integer> empIds);

    Clazz getById(Integer id);

//    @Update("update clazz c set c.name= #{name}, c.room = #{room},c.begin_date = #{beginDate},c.end_date = #{endDate},c.master_id = #{masterId}, c.subject = #{subject}" +
//            " where c.id=#{id}")
    void updateById(Clazz clazz);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Select("select c.name,c.id,c.create_time,c.update_time from clazz c")
    List<Clazz> getAll();
}
