package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.EmployeeMapper;
import com.volunteer.dao.mapper.OrgMapper;
import com.volunteer.pojo.po.Employee;
import com.volunteer.pojo.po.Org;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class OrgService extends AbstractService<Org> {

    @Autowired
    private OrgMapper orgMapper;

    public OrgService() {
        super(Org.class);
    }

    public Org getById(int id) {
        return this.orgMapper.getById(id);
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.orgMapper;
    }
}
