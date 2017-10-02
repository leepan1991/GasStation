package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface PermissionMapper extends AbstractMapper {

    int insert(Permission entity);

    int update(Permission entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Permission> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Permission entity);

    int count(@Param("entity") Permission entity);

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
