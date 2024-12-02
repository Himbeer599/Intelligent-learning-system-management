package com.learn.mapper;


import com.learn.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作working experience（pojo中的EmpExpr)
 */
@Mapper
//批量保存员工working experiences
public interface EmpExprMapper {
    //dynamic SQL query (batch results)
    void insertBatch(List<EmpExpr> exprList);
    void deleteByEmpIds(List<Integer> empIds);

}
