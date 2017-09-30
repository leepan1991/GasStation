package com.volunteer.api.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.Employee;
import com.volunteer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public ResponseData login(@RequestBody Employee employee) {
        LoginInfo loginInfo = employeeService.login(employee);
        if (null == loginInfo) {
            return ResponseData.error("账号或者密码错误");
        }
        return ResponseData.success("登陆成功", loginInfo);
    }

    /**
     * 退出
     * @return
     */
    @ResponseBody
    @RequestMapping("logout")
    public ResponseData logout(@RequestHeader("token") String token) {
        employeeService.logout(token);
        return ResponseData.success("退出成功", "");
    }
}
