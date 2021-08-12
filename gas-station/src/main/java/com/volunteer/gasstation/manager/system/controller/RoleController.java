package com.volunteer.gasstation.manager.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.ResourceConverter;
import com.volunteer.gasstation.manager.system.converter.RoleConverter;
import com.volunteer.gasstation.manager.system.dto.ResourceDTO;
import com.volunteer.gasstation.manager.system.dto.RoleDTO;
import com.volunteer.gasstation.manager.system.dto.RoleGrantDTO;
import com.volunteer.gasstation.manager.system.entity.Resource;
import com.volunteer.gasstation.manager.system.entity.Role;
import com.volunteer.gasstation.manager.system.entity.RoleResource;
import com.volunteer.gasstation.manager.system.entity.UserRole;
import com.volunteer.gasstation.manager.system.service.IResourceService;
import com.volunteer.gasstation.manager.system.service.IRoleResourceService;
import com.volunteer.gasstation.manager.system.service.IRoleService;
import com.volunteer.gasstation.manager.system.service.IUserRoleService;
import com.volunteer.gasstation.utils.ResourceUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private IResourceService resourceService;

    public RoleController(IRoleService roleService, IUserRoleService userRoleService, IRoleResourceService roleResourceService, IResourceService resourceService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.roleResourceService = roleResourceService;
        this.resourceService = resourceService;
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
        return new ResponseResult("添加成功", ResponseResult.ACTION_TOAST);
    }

    @PutMapping(value = "{id}")
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody RoleDTO record) {
        Role role = RoleConverter.INSTANCE.map(record);
        role.setId(id);
        roleService.updateById(role);
        return new ResponseResult("更新成功", ResponseResult.ACTION_TOAST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        roleService.removeById(id);
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper<UserRole>().lambda();
        queryWrapper.eq(UserRole::getRoleId, id);
        userRoleService.remove(queryWrapper);
        return new ResponseResult("删除成功", ResponseResult.ACTION_TOAST);
    }

    @GetMapping(value = "{id}/grantInfo")
    public ResponseResult<List<? extends ResourceDTO>> grantInfo(@PathVariable("id") Long id) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new QueryWrapper<RoleResource>().lambda();
        queryWrapper.eq(RoleResource::getRoleId, id);
        List<RoleResource> roleResourceList = roleResourceService.list(queryWrapper);

        LambdaQueryWrapper<Resource> resWrapper = new QueryWrapper<Resource>().lambda();
        resWrapper.orderByAsc(Resource::getSequence);
        List<ResourceDTO> resourceList = ResourceConverter.INSTANCE.mapList(resourceService.list(resWrapper));
        if (!CollectionUtils.isEmpty(roleResourceList)) {
            List<Long> resourceIdList = roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList());
            for (ResourceDTO resource : resourceList) {
                resource.setSelected(resourceIdList.contains(resource.getId()));
            }
        }

        return new ResponseResult<>(ResourceUtil.loop(resourceList));
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
        return new ResponseResult("授权成功", ResponseResult.ACTION_TOAST);
    }
}

