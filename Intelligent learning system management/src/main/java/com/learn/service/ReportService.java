package com.learn.service;

import com.learn.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();
}
