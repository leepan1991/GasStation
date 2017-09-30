package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.GasBottleMapper;
import com.volunteer.pojo.po.GasBottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class GasBottleService extends AbstractService<GasBottle> {

    @Autowired
    private GasBottleMapper gasBottleMapper;

    public GasBottleService() {
        super(GasBottle.class);
    }

    public int updateNextCheckTime(int[] ids, String nextCheckDate) {
        return this.gasBottleMapper.updateNextCheckTime(ids, nextCheckDate);
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.gasBottleMapper;
    }
}
