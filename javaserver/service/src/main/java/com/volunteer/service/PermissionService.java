package com.volunteer.service;

import com.volunteer.dao.mapper.PermissionMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Permission;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class PermissionService extends AbstractService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Permission entity) {
        return permissionMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Permission entity) {
        return permissionMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        permissionMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Permission entity) {
        TableData tableData = new TableData();
        tableData.data = this.permissionMapper.listPaged(parameter, entity);
        parameter.setTotal(this.permissionMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<Permission> selectInfoByUserId(int userId) {
        List<Permission> permissions = this.permissionMapper.selectInfoByUserId(userId);
        return this.arrangementLevel(permissions);
    }

    public List<Permission> selectMenuByUserId(int userId) {
        List<Permission> permissions = this.permissionMapper.selectMenuByUserId(userId);
        return this.arrangementLevel(permissions);
    }

    private List<Permission> loopPermission(int parentId, List<Permission> list) {
        List<Permission> permissions = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Permission child = list.get(i);
            if (child.getParentId() == parentId) {
                List<Permission> children = loopPermission(child.getId(), list);
                if (CollectionUtils.isNotEmpty(children)) {
                    child.setChildren(children);
                }
                permissions.add(child);
            }
        }
        return permissions;
    }

    private List<Permission> arrangementLevel(List<Permission> permissions) {
        List<Permission> list = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            Permission permission = permissions.get(i);
            if (permission.getParentId() == 0) {
                List<Permission> children = loopPermission(permission.getId(), permissions);
                if (CollectionUtils.isNotEmpty(children)) {
                    permission.setChildren(children);
                }
                list.add(permission);
            }
        }
        return list;
    }

    public List<Permission> selectLoopMenu() {
        List<Permission> permissions = this.permissionMapper.selectLoopMenu();
        return this.arrangementLevel(permissions);
    }
}
