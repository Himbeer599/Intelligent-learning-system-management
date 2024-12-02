package com.learn.mapper;

import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    public List<Clazz> list(ClazzQueryParam clazzQueryParam);
}
