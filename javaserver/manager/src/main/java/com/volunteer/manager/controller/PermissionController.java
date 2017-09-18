package com.volunteer.manager.controller;

import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.Permission;
import com.volunteer.service.AbstractService;
import com.volunteer.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends AbstractController<Permission> {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Permission entity) {
        this.getAbstractService().insert(entity);
        return this.response("添加成功", ResponseData.ACTION_TOAST);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Permission entity) {
        this.getAbstractService().update(entity);
        return this.response("修改成功", ResponseData.ACTION_TOAST);
    }

    @ResponseBody
    @RequestMapping(value = "selectLoopMenu", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectLoopMenu() {
        return this.response("成功", this.permissionService.selectLoopMenu());
    }


    @ResponseBody
    @RequestMapping(value = "selectInfoByUserId", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectInfoByUserId(HttpSession session) {
        ManageLoginUser loginUser = this.getLoginUser(session);
        return this.response("成功", this.permissionService.selectInfoByUserId(loginUser.getUser().getId()));
    }

    @Override
    public AbstractService<Permission> getAbstractService() {
        return permissionService;
    }
}
