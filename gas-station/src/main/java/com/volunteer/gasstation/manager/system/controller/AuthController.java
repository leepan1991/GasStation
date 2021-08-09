package com.volunteer.gasstation.manager.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.dto.LoginInfoDTO;
import com.volunteer.gasstation.manager.system.dto.LoginRequestDTO;
import com.volunteer.gasstation.manager.system.entity.User;
import com.volunteer.gasstation.manager.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author by huoyao on 2021/8/8.
 */
@RestController
@RequestMapping("mgr/auth")
public class AuthController extends BaseController {

    public final static String LOGIN_INFO = "LOGIN_INFO";

    private IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseResult login(@RequestBody LoginRequestDTO body, HttpSession session) {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(body.getUsername()), User::getUsername, body.getUsername());
        User user = userService.getOne(queryWrapper);
        if (null == user) {
            return new ResponseResult(ResponseResult.ERROR, "账号或密码不正确", ResponseResult.ACTION_TOAST);
        }
        if (!StringUtils.equals(user.getPassword(), body.getPassword())) {
            return new ResponseResult(ResponseResult.ERROR, "账号或密码不正确", ResponseResult.ACTION_TOAST);
        }
        LoginInfoDTO loginInfo = new LoginInfoDTO().setId(user.getId())
                .setName(user.getName())
                .setUsername(user.getUsername());
        session.setAttribute(LOGIN_INFO, loginInfo);
        return new ResponseResult(loginInfo);
    }

    @GetMapping("loginInfo")
    public ResponseResult<LoginInfoDTO> loginInfo(HttpSession session) {
        return new ResponseResult(session.getAttribute(LOGIN_INFO));
    }

    @PostMapping("logout")
    public ResponseResult logout(HttpSession session) {
        session.removeAttribute(LOGIN_INFO);
        return new ResponseResult("退出成功");
    }
}
