package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface UserMapper extends AbstractMapper {

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 更新用户状态
     *
     * @param user
     * @return
     */
    int updatePwd(User user);

    /** 更新用户状态
     *
     * @param user
     * @return
     */
    int updateAssignRole(User user);

    /** 删除分配的角色
     *
     * @param userId
     * @return
     */
    int deleteAssignRole(@Param("userId") int userId);
}
