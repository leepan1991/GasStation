package com.volunteer.gasstation.manager.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.PageResult;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.RoleConverter;
import com.volunteer.gasstation.manager.system.converter.UserConverter;
import com.volunteer.gasstation.manager.system.dto.*;
import com.volunteer.gasstation.manager.system.entity.User;
import com.volunteer.gasstation.manager.system.entity.UserRole;
import com.volunteer.gasstation.manager.system.enums.UserStatusEnum;
import com.volunteer.gasstation.manager.system.service.IRoleService;
import com.volunteer.gasstation.manager.system.service.IUserRoleService;
import com.volunteer.gasstation.manager.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/mgr/system/user")
public class UserController extends BaseController {

    private final IUserService userService;
    private final IUserRoleService userRoleService;
    private final IRoleService roleService;

    public UserController(IUserService userService, IUserRoleService userRoleService, IRoleService roleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseResult<PageResult<UserDTO>> list(UserListRequestDTO req) {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.like(StringUtils.isNotBlank(req.getUsername()), User::getUsername, req.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(req.getName()), User::getName, req.getName());
        queryWrapper.eq(User::getStatus, UserStatusEnum.NORMAL.intValue());
        Page<User> page = userService.page(req.convertMyBatisPlusPage(), queryWrapper);
        return new ResponseResult(PageResult.convert(page, UserConverter.INSTANCE.mapList(page.getRecords())));
    }

    @PostMapping
    public ResponseResult save(@RequestBody UserDTO record) {
        User user = UserConverter.INSTANCE.map(record);
        user.setCreateTime(LocalDateTime.now());
        user.setStatus(UserStatusEnum.NORMAL.intValue());
        userService.save(user);
        return new ResponseResult("添加成功", ResponseResult.ACTION_TOAST);
    }

    @PutMapping(value = "{id}")
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody UserDTO record) {
        User user = UserConverter.INSTANCE.map(record);
        user.setId(id);
        userService.updateById(user);
        return new ResponseResult("更新成功", ResponseResult.ACTION_TOAST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus(UserStatusEnum.DELETED.intValue());
        userService.updateById(user);
        return new ResponseResult("删除成功", ResponseResult.ACTION_TOAST);
    }

    @PutMapping(value = "{id}/password")
    public ResponseResult changePassword(@PathVariable("id") Long id, @RequestBody UserChangePasswordDTO params) {
        User user = new User();
        user.setId(id);
        user.setPassword(params.getPassword());
        userService.updateById(user);
        return new ResponseResult("密码更新成功", ResponseResult.ACTION_TOAST);
    }

    @GetMapping(value = "{id}/grantInfo")
    public ResponseResult<List<GrantedRoleDTO>> roleList(@PathVariable("id") Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper<UserRole>().lambda();
        queryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoleList = userRoleService.list(queryWrapper);

        List<GrantedRoleDTO> roleList = RoleConverter.INSTANCE.mapGrantRoleList(roleService.list());
        if (!CollectionUtils.isEmpty(userRoleList)) {
            List<Long> selectRoleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            for (GrantedRoleDTO role : roleList) {
                role.setSelected(selectRoleIdList.contains(role.getId()));
            }
        }
        return new ResponseResult(roleList);
    }

    @PutMapping(value = "{id}/grant")
    public ResponseResult grantRole(@PathVariable("id") Long id, @RequestBody UserGrantDTO record) {
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper<UserRole>().lambda();
        queryWrapper.eq(UserRole::getUserId, id);
        userRoleService.remove(queryWrapper);

        if (!CollectionUtils.isEmpty(record.getRoleIdList())) {
            List<UserRole> userRoleList = new ArrayList<>();
            for (Long roleId : record.getRoleIdList()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
            userRoleService.saveBatch(userRoleList);
        }
        return new ResponseResult("授权成功", ResponseResult.ACTION_TOAST);
    }
}

