package com.learn.mapper;

import com.learn.pojo.Student;
import com.learn.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void add(Student student);

    @Select("select s.* from student s where s.id = #{id} ")
    Student getById(Integer id);

    void updateById(Student student);

    void deleteByIds(List<Integer> ids);

    @Select("SELECT COUNT(*) FROM student WHERE id = #{id}")
    int countByClazzId(@Param("id") Integer id);
}
