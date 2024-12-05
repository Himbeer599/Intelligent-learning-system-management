package com.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.learn.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);
}
