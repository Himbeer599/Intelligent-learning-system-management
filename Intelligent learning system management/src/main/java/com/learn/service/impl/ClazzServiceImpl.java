package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.*;
import com.learn.service.ClazzService;
import com.learn.service.EmpLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
//        log.info("info of clazzList{}", clazzList);

        LocalDate currentDate = LocalDate.now();
//        System.out.println("Current Date: " + currentDate);

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
//            System.out.println("Class ID: " + clazz.getId() + ", Status: " + clazz.getStatus());
        }
        /**
         * although here not used, but it's quite important to know how to get Status in a List (use Stream!)
         */
        List<String> statuses = clazzList.stream()
                .map(Clazz::getStatus)
                .toList();
//        System.out.println("Statuses: " + statuses);

        Page<Clazz> c = (Page<Clazz>) clazzList;
//        log.info("info of c{}", c);
        return new PageResult(c.getTotal(),c.getResult());
    }



    /**
     * when I want to create a new class, that means two things:
     * (1)display all emps in the empID optiop
     *      --firstly, define method (getAll) in Emp files(Getmapping in EmpController-->getAll method in EmpServiceImpl-->define sql in EmpMapper)
     *          remember to return empList
     *      -- secondly, dispatch this method in ClazzServiceImpl to get all empList
     * (2)set empId of empList (use Stream.map.List) as clazz ID
     *      -- define updatedMasterIds method in Mapper (use foreach cycle to set empId as masterId which were reveived from empList)
     *      --dispatch updateMasterIds in Service and then insert
     *  --for this function, there are two methods defined in ClazzMapper, one is insert for update, the other is updateID.
     *      Besides, there's still a method to getAll defined in EmpMapper, EmpService, and EmpController
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void creatClazz(Clazz clazz) {

        //save general info of classes
        try {
//            here needs to dispatch the method of getAll to show all the masterId(empId)
            List<Emp> empList = empMapper.getAll();
            log.info("empList: {}", empList);

            List<Integer> empIds = empList.stream()
                    .map(Emp::getId)
                    .toList();
            log.info("empIds: {}", empIds);

            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());
            clazzMapper.updateMasterIds(empIds);
            clazzMapper.insert(clazz);
            log.info("clazz: {}", clazz);
        } finally {
            EmpLog clazzLog = new EmpLog(null, LocalDateTime.now(),"info of new added classes is shown below:"+clazz);
            empLogService.insertLog(clazzLog);
        }
    }
}
