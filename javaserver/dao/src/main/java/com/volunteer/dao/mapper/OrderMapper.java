package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Order;
import com.volunteer.pojo.po.OrderBottle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface OrderMapper extends AbstractMapper {

    int insert(Order entity);

    int update(Order entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Order> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Order entity);

    int count(@Param("entity") Order entity);

    List<Order> listAvailable(@Param("orgId") int orgId);

    int insertOrderBottle(OrderBottle orderBottle);
}
