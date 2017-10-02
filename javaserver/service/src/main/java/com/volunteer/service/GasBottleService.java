package com.volunteer.service;

import com.volunteer.dao.mapper.GasBottleMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.dto.GasBottleDTO;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.OrderBottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class GasBottleService extends AbstractService {

    @Autowired
    private GasBottleMapper gasBottleMapper;

    public int updateNextCheckTime(int[] ids, String nextCheckDate) {
        return this.gasBottleMapper.updateNextCheckTime(ids, nextCheckDate);
    }

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(GasBottle entity) {
        return gasBottleMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(GasBottle entity) {
        return gasBottleMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids, int orgId) {
        gasBottleMapper.deleteByIds(ids, orgId);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, GasBottle entity) {
        TableData tableData = new TableData();
        tableData.data = this.gasBottleMapper.listPaged(parameter, entity);
        parameter.setTotal(this.gasBottleMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<GasBottle> listPagedWhenExport(TableParameter parameter, GasBottle entity) {
        return this.gasBottleMapper.listPaged(parameter, entity);
    }

    /**
     * 入库
     * @param gasBottle
     * @return
     */
    public void storage(GasBottleDTO gasBottle) {
        GasBottle entity = gasBottleMapper.getById(gasBottle.getOrgId(), gasBottle.getCode());
        gasBottle.setOrderId(entity.getOrderId());
        this.gasBottleMapper.updateWhenStorage(gasBottle);

        OrderBottle orderBottle = new OrderBottle();
        orderBottle.setOrderId(entity.getOrderId());
        orderBottle.setBottleCode(entity.getCode());
        this.gasBottleMapper.updateDealWhenStorage(orderBottle);
    }

    /**
     * 出库
     * @param gasBottle
     */
    public void delivery(GasBottleDTO gasBottle) {
        this.gasBottleMapper.updateWhenDelivery(gasBottle);

        OrderBottle orderBottle = new OrderBottle();
        orderBottle.setOrderId(gasBottle.getOrderId());
        orderBottle.setBottleCode(gasBottle.getCode());
        this.gasBottleMapper.updateDealWhenDelivery(orderBottle);
    }
}
