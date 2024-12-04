package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.exception.ClazzDeleteException;
import com.learn.mapper.ClazzMapper;
import com.learn.mapper.EmpMapper;
import com.learn.mapper.StudentMapper;
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
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private StudentMapper studentMapper;
  /*  @Autowired
    private EmpMapper empMapper;*/

    /**
     * fetch all clazz:
     * PageResult<Clazz>:{total count:..,rows:[{Clazz1},{Clazz2},{Clazz3}..]}
     *      --firstly, get clazz info by list method(select c., mastername from emp) defined in ClazzMapper
     *      --secondly, setStatus. get Clazz from clazzlist, then setStatus in clazz
     *      --thirdly, convert clazzList to Page<Clazz>
     *       --return PageResult
     * @param clazzQueryParam
     * @return
     */
    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        LocalDate currentDate = LocalDate.now();
        //setStatus needs to be employed on clazz (which needs to get from clazzList)
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate().isBefore(currentDate)) {
                clazz.setStatus("finished");
            } else if (clazz.getBeginDate().isAfter(currentDate)) {
                clazz.setStatus("not start");
            } else {
                clazz.setStatus("teaching");
            }
        }
        Page<Clazz> c = (Page<Clazz>) clazzList;
        return new PageResult(c.getTotal(),c.getResult());
    }

    /**
     * when I want to create a new class, that means two things:
     * (1)display all emps in the empID option (this step automatically getemp data because of Getmapping in EmpController)
     * remember: the following second steps are not necessary. Here it doesn't need to setId, thereby not necessary to disptach getAll method.
     *      --firstly, define method (getAll) in Emp files(Getmapping in EmpController-->getAll method in EmpServiceImpl-->define sql in EmpMapper)
     *          remember to return empList(only contains e.name namely masterName, and id namely empId)
     *      -- secondly, dispatch this method in ClazzServiceImpl to get all empList (falsch)
     * (2)set empId of empList (use Stream.map.List) as clazz ID--falsh
     *      -- define updatedMasterIds method in Mapper (use foreach cycle to set empId as masterId which were reveived from empList)
     *      --dispatch updateMasterIds in Service and then insert
     *  --for this function, there are two methods defined in ClazzMapper, one is insert for update, the other is updateID.
     *      Besides, there's still a method to getAll defined in EmpMapper, EmpService, and EmpController
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void creatClazz(Clazz clazz) {
        try {
          /*  List<Emp> empList = empMapper.getAll();
            log.info("empList: {}", empList);
            List<Integer> empIds = empList.stream()
                    .map(Emp::getId)
                    .toList();
            log.info("empIds: {}", empIds);*/
        //save general info of classes
            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());
//            clazzMapper.updateMasterIds(empIds);
            clazzMapper.insert(clazz);
        } finally {
            EmpLog clazzLog = new EmpLog(null, LocalDateTime.now(),"info of new added classes is shown below:"+clazz);
            empLogService.insertLog(clazzLog);
        }
    }

    /**
     * When editing /revising a clazz:
     *      --step 1: getById(when click on "Edit", the relevant info can be displayed)
     *      --step2: update
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
//        list = Arrays.asList(clazz.getId());
    }

    @Override
    public void deleteById(Integer id) {
        int studentCount = studentMapper.countByClazzId(id);
        System.out.println("studentCount:"+studentCount);
        if (studentCount > 1) {
            throw new ClazzDeleteException();
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
