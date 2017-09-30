package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface RoleMapper extends AbstractMapper {

    List<Role> selectWhenAssignRole(@Param("userId") int userId);

    /**
     * 批量创建权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    int batchInsertAcl(Role role);

    /**
     * 删除权限
     * @param roleId
     * @return
     */
    int deleteAclByRoleId(@Param("roleId") int roleId);
}
