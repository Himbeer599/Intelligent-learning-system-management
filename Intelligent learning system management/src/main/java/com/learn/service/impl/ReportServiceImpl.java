package com.learn.service.impl;

import com.learn.mapper.EmpMapper;
import com.learn.mapper.StudentMapper;
import com.learn.pojo.ClazzType;
import com.learn.pojo.JobOption;
import com.learn.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzType getStuClazzData() {
        List<Map<String,Object>> list =studentMapper.countStuClazzData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzname")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new ClazzType(clazzList,dataList);
    }
}
