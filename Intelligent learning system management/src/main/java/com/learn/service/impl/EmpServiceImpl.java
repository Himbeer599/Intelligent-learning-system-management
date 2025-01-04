package com.learn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.mapper.EmpExprMapper;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.*;
import com.learn.service.EmpLogService;
import com.learn.service.EmpService;
import com.learn.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service

public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    private List<Integer> list;

    /**
    @Override
    public PageResult page(Integer page, Integer pageSize){
         * 原始分页查询
        //获取总记录数
        Long total = empMapper.count();
        //获取列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.list(start,pageSize);
        //封装结果
        return new PageResult(total,empList);*/

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        //save general info of employees
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //save working experiences (several working experiences) of employees
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"info of new added employee is shown below:"+emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
       return empMapper.getById(id);
    }

    @Transactional (rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        list = Arrays.asList(emp.getId());//将id封装为一个list，使用ARRAYS.ASlIST
        empExprMapper.deleteByEmpIds(list);

        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    public boolean checkPassword(Integer id,String password) {
        return empMapper.checkPassword(id, password);
    }

    @Override
    public void updatePassword(EmpPassword empPassword) {
//        empPassword.setUpdateTime(LocalDateTime.now());
            empMapper.updatePassword(empPassword);
    }

    @Override
    public List<EmpId> getAll() {
        return empMapper.getAll();
    }

    @Override
    public LoginInfo login(Emp emp){
        Emp empLogin = empMapper.selectByUsernameandpassword(emp);

        if(empLogin != null){
//            String hashedPassword = empLogin.getPassword();

//            if (BCrypt.checkpw(emp.getPassword(), hashedPassword)) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", empLogin.getId());
                dataMap.put("username", empLogin.getUsername());

                String jwt = JwtUtils.generateJwt(dataMap);
                LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt, empLogin.getUserRole());
                return loginInfo;
//            }
        }
        return null;
    }

//    @Override
//    public RegisterInfo register(Emp emp){
//        Emp empRegister = empMapper.selectByUsernameAndPassword(emp);
//
//        if(empRegister != null){
//            return null;
//        }
//        empMapper.insert(emp);
//        RegisterInfo registerInfo = new RegisterInfo(empRegister.getId(),empRegister.getUsername(),empRegister.getName());
//        return registerInfo;
//    }

}
