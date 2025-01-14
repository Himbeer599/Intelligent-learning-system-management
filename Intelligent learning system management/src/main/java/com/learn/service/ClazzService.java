package com.learn.service;

import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import com.learn.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void creatClazz(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> getAll();
}
