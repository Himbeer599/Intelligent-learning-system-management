package com.learn.mapper;

import com.learn.pojo.Student;
import com.learn.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(name, no, gender, phone, id_card, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void add(Student student);

    @Select("select s.* from student s where s.id = #{id} ")
    Student getById(Integer id);

    void updateById(Student student);

    void deleteByIds(List<Integer> ids);

    @Select("SELECT COUNT(*) FROM student s WHERE s.clazz_id = #{id}")
    int countByClazzId(@Param("id") Integer id);

    @MapKey("clazzname")
    List<Map<String, Object>> countStuClazzData();
    @MapKey("name")
    List<Map> countStuDegreeData();
}
