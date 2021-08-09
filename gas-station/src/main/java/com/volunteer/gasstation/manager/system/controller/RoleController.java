package com.volunteer.gasstation.manager.system.controller;


import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.RoleConverter;
import com.volunteer.gasstation.manager.system.dto.RoleDTO;
import com.volunteer.gasstation.manager.system.entity.Role;
import com.volunteer.gasstation.manager.system.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
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
        return new ResponseResult("删除成功");
    }
}

