package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface UserMapper extends AbstractMapper {

    int insert(User entity);

    int update(User entity);

    int deleteByIds(@Param("ids") Object ids);

    List<User> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") User entity);

    int count(@Param("entity") User entity);

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
