package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface PermissionMapper extends AbstractMapper {

    /**
     *
     * @param userId
     * @return
     */
    List<Permission> selectInfoByUserId(@Param("userId")  int userId);

    /**
     * 根据userId获得权限范围内的Permission
     * @param userId
     * @return
     */
    List<Permission> selectMenuByUserId(@Param("userId")  int userId);

    /**
     * 通过父ID查询
     * @return
     */
    List<Permission> selectLoopMenu();
}
