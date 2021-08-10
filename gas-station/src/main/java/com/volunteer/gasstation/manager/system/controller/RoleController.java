package com.volunteer.gasstation.manager.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.RoleConverter;
import com.volunteer.gasstation.manager.system.dto.RoleDTO;
import com.volunteer.gasstation.manager.system.dto.RoleGrantDTO;
import com.volunteer.gasstation.manager.system.entity.Role;
import com.volunteer.gasstation.manager.system.entity.RoleResource;
import com.volunteer.gasstation.manager.system.entity.UserRole;
import com.volunteer.gasstation.manager.system.service.IRoleResourceService;
import com.volunteer.gasstation.manager.system.service.IRoleService;
import com.volunteer.gasstation.manager.system.service.IUserRoleService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author slzc
 * @since 2021-08-08
 */
@RestController
@RequestMapping("/mgr/system/role")
public class RoleController extends BaseController {

    private IRoleService roleService;
    private IUserRoleService userRoleService;
    private IRoleResourceService roleResourceService;

    public RoleController(IRoleService roleService, IUserRoleService userRoleService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseResult<List<RoleDTO>> list() {
        return new ResponseResult(RoleConverter.INSTANCE.mapList(roleService.list()));
    }

    @PostMapping
    public ResponseResult save(@RequestBody RoleDTO record) {
        Role role = RoleConverter.INSTANCE.map(record);
        role.setCreateTime(LocalDateTime.now());
        roleService.save(role);
        return new ResponseResult("添加成功");
    }

    @PutMapping(value = "{id}")
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody RoleDTO record) {
        Role role = RoleConverter.INSTANCE.map(record);
        role.setId(id);
        roleService.updateById(role);
        return new ResponseResult("更新成功");
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        roleService.removeById(id);
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper<UserRole>().lambda();
        queryWrapper.eq(UserRole::getRoleId, id);
        userRoleService.remove(queryWrapper);
        return new ResponseResult("删除成功");
    }

    @PutMapping(value = "{id}/grant")
    public ResponseResult grantResource(@PathVariable("id") Long id, @RequestBody RoleGrantDTO record) {
        LambdaQueryWrapper<RoleResource> deleteWrapper = new QueryWrapper<RoleResource>().lambda();
        deleteWrapper.eq(RoleResource::getRoleId, id);
        roleResourceService.remove(deleteWrapper);

        if (!CollectionUtils.isEmpty(record.getResourceIdList())) {
            List<RoleResource> roleResourceList = new ArrayList<>();
            for (Long resourceId : record.getResourceIdList()) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(id);
                roleResource.setResourceId(resourceId);
                roleResourceList.add(roleResource);
            }
            roleResourceService.saveBatch(roleResourceList);
        }
        return new ResponseResult("授权成功");
    }
}

