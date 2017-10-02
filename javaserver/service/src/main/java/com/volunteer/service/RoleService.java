package com.volunteer.service;

import com.volunteer.dao.mapper.RoleMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class RoleService extends AbstractService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Role entity) {
        return roleMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Role entity) {
        return roleMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        roleMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Role entity) {
        TableData tableData = new TableData();
        tableData.data = this.roleMapper.listPaged(parameter, entity);
        parameter.setTotal(this.roleMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<Role> selectWhenAssignRole(int userId) {
        return this.roleMapper.selectWhenAssignRole(userId);
    }

    public int batchInsertAcl(Role role) {
        this.roleMapper.deleteAclByRoleId(role.getId());
        return this.roleMapper.batchInsertAcl(role);
    }
}
