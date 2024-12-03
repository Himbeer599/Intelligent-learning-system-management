package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Clazz;
import com.learn.pojo.ClazzQueryParam;
import com.learn.pojo.PageResult;
import com.learn.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        log.info("info of clazzList{}", clazzList);

        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);

        /**
         * ！！how to change the status based on the evalution of date!Pay attention to for cycle
        * */
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate().isBefore(currentDate)) {
                clazz.setStatus("finished");
            } else if (clazz.getBeginDate().isAfter(currentDate)) {
                clazz.setStatus("not start");
            } else {
                clazz.setStatus("teaching");
            }
            System.out.println("Class ID: " + clazz.getId() + ", Status: " + clazz.getStatus());
        }
/**
 * although here not used, but it's quite important to know how to get Status in a List (use Stream!)
 */
        List<String> statuses = clazzList.stream()
                .map(Clazz::getStatus)
                .toList();
        System.out.println("Statuses: " + statuses);

        Page<Clazz> c = (Page<Clazz>) clazzList;
        log.info("info of c{}", c);
        return new PageResult(c.getTotal(),c.getResult());
    }
}
