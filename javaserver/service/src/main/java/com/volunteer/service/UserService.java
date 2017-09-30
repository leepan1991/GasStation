package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.UserMapper;
import com.volunteer.pojo.po.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class UserService extends AbstractService<User> {

    @Autowired
    private UserMapper userMapper;

    public UserService() {
        super(User.class);
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

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.userMapper;
    }
}
