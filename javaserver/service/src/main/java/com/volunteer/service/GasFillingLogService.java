package com.volunteer.service;

import com.volunteer.dao.mapper.GasBottleMapper;
import com.volunteer.dao.mapper.GasFillingLogMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.GasFillingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class GasFillingLogService extends AbstractService {

    @Autowired
    private GasFillingLogMapper gasFillingLogMapper;
    @Autowired
    private GasBottleMapper gasBottleMapper;

    public int insert(GasFillingLog entity) {
        int count = gasFillingLogMapper.insert(entity);
        GasBottle gasBottle = new GasBottle();
        gasBottle.setOrgId(entity.getOrgId());
        gasBottle.setCode(entity.getCode());
        gasBottle.setLatitude(entity.getLatitude());
        gasBottle.setLongitude(entity.getLongitude());
        gasBottleMapper.updateLocation(gasBottle);
        return count;
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(GasFillingLog entity) {
        return gasFillingLogMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        gasFillingLogMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, GasFillingLog entity) {
        TableData tableData = new TableData();
        tableData.data = this.gasFillingLogMapper.listPaged(parameter, entity);
        parameter.setTotal(this.gasFillingLogMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<GasFillingLog> listPagedWhenExport(TableParameter parameter, GasFillingLog entity) {
        return this.gasFillingLogMapper.listPaged(parameter, entity);
    }
}
