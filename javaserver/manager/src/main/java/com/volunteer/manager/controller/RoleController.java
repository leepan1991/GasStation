package com.volunteer.manager.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Role;
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
public class RoleController extends AbstractController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Role role) {
        this.roleService.insert(role);
        return ResponseData.success("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Role role) {
        this.roleService.update(role);
        return ResponseData.success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.roleService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, Role entity) {
        return ResponseData.success("OK", this.roleService.listPaged(parameter, entity));
    }

    @ResponseBody
    @RequestMapping(value = "selectWhenAssignRole", method = { RequestMethod.GET })
    public ResponseData selectWhenAssignRole(int userId) {
        List<Role> roles = this.roleService.selectWhenAssignRole(userId);
        return ResponseData.success("修改成功", roles);
    }

    @ResponseBody
    @RequestMapping(value = "assignPermission", method = { RequestMethod.POST })
    public ResponseData assignPermission(@RequestBody  Role role) {
        this.roleService.batchInsertAcl(role);
        return ResponseData.success("权限分配成功");
    }
}
