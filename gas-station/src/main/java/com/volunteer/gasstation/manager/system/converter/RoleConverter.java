package com.volunteer.gasstation.manager.system.converter;

import com.volunteer.gasstation.manager.system.dto.GrantedRoleDTO;
import com.volunteer.gasstation.manager.system.dto.RoleDTO;
import com.volunteer.gasstation.manager.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/8.
 */
@Mapper
public interface RoleConverter {
    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    /**
     * Role转RoleDTO
     *
     * @param role
     * @return
     */
    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    RoleDTO map(Role role);

    /**
     * Role转GrantedRoleDTO
     * @param role
     * @return
     */
    GrantedRoleDTO mapGrantRole(Role role);

    /**
     * RoleDTO转Role
     *
     * @param role
     * @return
     */
    Role map(RoleDTO role);

    /**
     * Role转RoleDTO
     *
     * @param list
     * @return
     */
    List<RoleDTO> mapList(List<Role> list);

    /**
     * Role转GrantedRoleDTO
     * @param list
     * @return
     */
    List<GrantedRoleDTO> mapGrantRoleList(List<Role> list);
}
