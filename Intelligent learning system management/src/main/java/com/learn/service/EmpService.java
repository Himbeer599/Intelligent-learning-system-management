package com.learn.service;

import com.learn.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;
//import com.learn.pojo.RegisterInfo;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    void updatePassword (EmpPassword empPassword);
    List<EmpId> getAll();
    boolean checkPassword(Integer id,String password);
    LoginInfo login(Emp emp);

//    RegisterInfo register(Emp emp);
}
