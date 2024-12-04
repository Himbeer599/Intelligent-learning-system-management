package com.learn.service.impl;

import com.learn.mapper.EmpMapper;
import com.learn.mapper.StudentMapper;
import com.learn.pojo.ClazzType;
import com.learn.pojo.JobOption;
import com.learn.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
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

    @Override
    public List<Map> getStuDegreeData() {
//        return studentMapper.countStuDegreeData();
//        List<Map<String,Object>> list =studentMapper.countStuDegreeData();
//        List<Object> studentList = list.stream().map(dataMap -> dataMap.get("degree")).toList();
//        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
//        log.info("info:{},{}",studentList,dataList);
        return studentMapper.countStuDegreeData();
    }
}
