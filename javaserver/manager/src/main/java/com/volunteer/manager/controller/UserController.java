package com.volunteer.manager.controller;

import com.volunteer.manager.web.AclFilter;
import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Permission;
import com.volunteer.pojo.po.Role;
import com.volunteer.pojo.po.User;
import com.volunteer.service.AbstractService;
import com.volunteer.service.PermissionService;
import com.volunteer.service.UserService;
import com.volunteer.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping("login")
    public ResponseData login(HttpSession session, @RequestBody  User user) {
        ResponseData responseData = new ResponseData();
        user = userService.login(user);
        if (user == null) {
            responseData.message = "用户名或密码错误";
            responseData.code = ResponseData.CODE_ERROR;
        } else {
            List<Permission> permissionList = permissionService.selectMenuByUserId(user.getId());
            ManageLoginUser manageLoginUser = new ManageLoginUser(user, permissionList);
            session.setAttribute(AclFilter.LOGIN_USER, manageLoginUser);
            responseData.message = "登录成功";
            responseData.data = manageLoginUser;
            responseData.code = ResponseData.CODE_SUCCESS;
        }
        return responseData;
    }

    @ResponseBody
    @RequestMapping("logout")
    public ResponseData logout(HttpSession session) {
        ResponseData responseData = new ResponseData();
        session.removeAttribute(AclFilter.LOGIN_USER);
        session.invalidate();
        return responseData;
    }

    @ResponseBody
    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public ResponseData userInfo(HttpSession session) {
        ResponseData responseData = new ResponseData();
        ManageLoginUser user = this.getLoginUser(session);
        if (user == null) {
            responseData.message = "请登录";
            responseData.code = ResponseData.CODE_LOGIN_NEED;
        } else {
            responseData.data = user;
            responseData.code = ResponseData.CODE_SUCCESS;
        }
        return responseData;
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody User user) {
        this.userService.insert(user);
        return ResponseData.success("添加用户成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody User user) {
        this.userService.update(user);
        return ResponseData.success("修改用户成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.userService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, User entity) {
        return ResponseData.success("OK", this.userService.listPaged(parameter, entity));
    }

    @ResponseBody
    @RequestMapping(value = "updatePwd")
    public ResponseData updatePwd(@RequestBody User user) {
        this.userService.updatePwd(user);
        return ResponseData.success("修改密码成功");
    }

    @ResponseBody
    @RequestMapping(value = "assignRole")
    public ResponseData assignRole(@RequestBody User user) {
        this.userService.updateAssignRole(user);
        return ResponseData.success("修改密码成功");
    }
}
