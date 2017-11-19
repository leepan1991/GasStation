package com.volunteer.service;

import com.volunteer.dao.mapper.BottleLocationMapper;
import com.volunteer.dao.mapper.GasBottleMapper;
import com.volunteer.dao.mapper.OrderMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.dto.OrderBottleDTO;
import com.volunteer.pojo.po.BottleLocation;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.Order;
import com.volunteer.pojo.po.OrderBottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class OrderService extends AbstractService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GasBottleMapper gasBottleMapper;
    @Autowired
    private BottleLocationMapper bottleLocationMapper;

    /**
     * 查找可用的订单
     *
     * @param orgId
     * @return
     */
    public List<Order> listAvailable(int orgId) {
        return orderMapper.listAvailable(orgId);
    }

    public int delivery(OrderBottleDTO orderBottle) {
        gasBottleMapper.updateDealWhenDelivery(orderBottle);
        GasBottle gasBottle = new GasBottle();
        gasBottle.setOrgId(orderBottle.getOrgId());
        gasBottle.setCode(orderBottle.getBottleCode());
        gasBottle.setOrderId(orderBottle.getOrderId());
        gasBottle.setLatitude(orderBottle.getLatitude());
        gasBottle.setLongitude(orderBottle.getLongitude());
        gasBottleMapper.updateLocation(gasBottle);

        BottleLocation location = new BottleLocation();
        location.setLat(orderBottle.getLatitude());
        location.setLng(orderBottle.getLongitude());
        location.setOrderId(orderBottle.getOrderId());
        location.setBottleCode(orderBottle.getBottleCode());
        bottleLocationMapper.insert(location);

        return gasBottleMapper.updateWhenDelivery(gasBottle);
    }

    public int storage(OrderBottleDTO orderBottle) {
        GasBottle gasBottle = gasBottleMapper.getById(orderBottle.getOrgId(), orderBottle.getBottleCode());
        orderBottle.setOrderId(gasBottle.getOrderId());
        gasBottleMapper.updateDealWhenStorage(orderBottle);
        gasBottle.setLatitude(orderBottle.getLatitude());
        gasBottle.setLongitude(orderBottle.getLongitude());
        gasBottleMapper.updateLocation(gasBottle);

        BottleLocation location = new BottleLocation();
        location.setLat(orderBottle.getLatitude());
        location.setLng(orderBottle.getLongitude());
        location.setOrderId(orderBottle.getOrderId());
        location.setBottleCode(orderBottle.getBottleCode());
        bottleLocationMapper.insert(location);

        return gasBottleMapper.updateWhenStorage(gasBottle);
    }

    public int record(OrderBottleDTO orderBottle) {
        GasBottle gasBottle = new GasBottle();
        gasBottle.setOrgId(orderBottle.getOrgId());
        gasBottle.setCode(orderBottle.getBottleCode());
        gasBottle.setLatitude(orderBottle.getLatitude());
        gasBottle.setLongitude(orderBottle.getLongitude());
        gasBottleMapper.updateLocation(gasBottle);

        BottleLocation location = new BottleLocation();
        location.setLat(orderBottle.getLatitude());
        location.setLng(orderBottle.getLongitude());
        location.setOrderId(orderBottle.getOrderId());
        location.setBottleCode(orderBottle.getBottleCode());
        return bottleLocationMapper.insert(location);
    }

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Order entity) {
        entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        entity.setStatus((short) 0);
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
