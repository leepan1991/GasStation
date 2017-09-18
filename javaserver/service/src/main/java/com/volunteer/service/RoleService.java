package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.RoleMapper;
import com.volunteer.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class RoleService extends AbstractService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    public RoleService() {
        super(Role.class);
    }


    public List<Role> selectWhenAssignRole(int userId) {
        return this.roleMapper.selectWhenAssignRole(userId);
    }

    public int batchInsertAcl(Role role) {
        this.roleMapper.deleteAclByRoleId(role.getId());
        return this.roleMapper.batchInsertAcl(role);
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.roleMapper;
    }
}
