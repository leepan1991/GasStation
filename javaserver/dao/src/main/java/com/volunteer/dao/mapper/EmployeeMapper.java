package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface EmployeeMapper extends AbstractMapper {

    int insert(Employee entity);

    int update(Employee entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Employee> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Employee entity);

    int count(@Param("entity") Employee entity);

    Employee findByPhone(@Param("phone") String phone);
}
