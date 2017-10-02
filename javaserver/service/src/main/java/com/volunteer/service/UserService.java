package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.UserMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class UserService extends AbstractService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(User entity) {
        return userMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(User entity) {
        return userMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        userMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, User entity) {
        TableData tableData = new TableData();
        tableData.data = this.userMapper.listPaged(parameter, entity);
        parameter.setTotal(this.userMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public User login(User user) {
        return userMapper.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 更新用户密码
     *
     * @param user
     * @return
     */
    public int updatePwd(User user) {
        return this.userMapper.updatePwd(user);
    }

    /**
     * 更新用户密码
     *
     * @param user
     * @return
     */
    public int updateAssignRole(User user) {
        this.userMapper.deleteAssignRole(user.getId());
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            return this.userMapper.updateAssignRole(user);
        }
        return 0;
    }
}
