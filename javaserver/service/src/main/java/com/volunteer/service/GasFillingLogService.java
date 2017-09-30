package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.GasBottleMapper;
import com.volunteer.dao.mapper.GasFillingLogMapper;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.GasFillingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class GasFillingLogService extends AbstractService<GasFillingLog> {

    @Autowired
    private GasFillingLogMapper gasFillingLogMapper;
    @Autowired
    private GasBottleMapper gasBottleMapper;

    public GasFillingLogService() {
        super(GasFillingLog.class);
    }

    @Override
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

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.gasFillingLogMapper;
    }
}
