package com.volunteer.service;

import com.volunteer.dao.mapper.OrderMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class OrderService extends AbstractService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查找可用的订单
     * @param orgId
     * @return
     */
    public List<Order> listAvailable(int orgId) {
        return orderMapper.listAvailable(orgId);
    }

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Order entity) {
        return orderMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Order entity) {
        return orderMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        orderMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Order entity) {
        TableData tableData = new TableData();
        tableData.data = this.orderMapper.listPaged(parameter, entity);
        parameter.setTotal(this.orderMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }
}
