package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface CustomerMapper extends AbstractMapper {

    int insert(Customer entity);

    int update(Customer entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Customer> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Customer entity);

    int count(@Param("entity") Customer entity);
}
