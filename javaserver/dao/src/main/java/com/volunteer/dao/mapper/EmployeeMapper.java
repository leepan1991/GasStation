package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface EmployeeMapper extends AbstractMapper {

    Employee findByPhone(@Param("phone") String phone);
}
