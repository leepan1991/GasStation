package com.volunteer.manager.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.pojo.po.Role;
import com.volunteer.service.AbstractService;
import com.volunteer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("role")
public class RoleController extends AbstractController<Role> {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Role role) {
        this.getAbstractService().insert(role);
        return this.response("添加成功", ResponseData.ACTION_TOAST);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Role role) {
        this.getAbstractService().update(role);
        return this.response("修改成功", ResponseData.ACTION_TOAST);
    }

    @ResponseBody
    @RequestMapping(value = "selectWhenAssignRole", method = { RequestMethod.GET })
    public ResponseData selectWhenAssignRole(int userId) {
        List<Role> roles = this.roleService.selectWhenAssignRole(userId);
        return this.response("修改成功", roles);
    }

    @ResponseBody
    @RequestMapping(value = "assignPermission", method = { RequestMethod.POST })
    public ResponseData assignPermission(@RequestBody  Role role) {
        this.roleService.batchInsertAcl(role);
        return this.response("权限分配成功", null);
    }

    @Override
    public AbstractService<Role> getAbstractService() {
        return roleService;
    }
}
