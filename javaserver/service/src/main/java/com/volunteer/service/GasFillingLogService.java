package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.GasFillingLogMapper;
import com.volunteer.pojo.GasFillingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class GasFillingLogService extends AbstractService<GasFillingLog> {

    @Autowired
    private GasFillingLogMapper gasFillingLogMapper;

    public GasFillingLogService() {
        super(GasFillingLog.class);
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.gasFillingLogMapper;
    }
}
