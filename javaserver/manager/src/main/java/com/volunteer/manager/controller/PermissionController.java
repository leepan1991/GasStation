package com.volunteer.manager.controller;

import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Permission;
import com.volunteer.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends AbstractController {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Permission entity) {
        this.permissionService.insert(entity);
        return ResponseData.success("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Permission entity) {
        this.permissionService.update(entity);
        return ResponseData.success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.permissionService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, Permission entity) {
        return ResponseData.success("OK", this.permissionService.listPaged(parameter, entity));
    }

    @ResponseBody
    @RequestMapping(value = "selectLoopMenu", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectLoopMenu() {
        return ResponseData.success("成功", this.permissionService.selectLoopMenu());
    }


    @ResponseBody
    @RequestMapping(value = "selectInfoByUserId", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectInfoByUserId(HttpSession session) {
        ManageLoginUser loginUser = this.getLoginUser(session);
        return ResponseData.success("成功", this.permissionService.selectInfoByUserId(loginUser.getUser().getId()));
    }
}
