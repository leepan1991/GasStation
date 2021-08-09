package com.volunteer.gasstation.core.interceptor;

import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.controller.AuthController;
import com.volunteer.gasstation.manager.system.dto.LoginInfoDTO;
import com.volunteer.gasstation.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author by huoyao on 2021/8/8.
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession().getAttribute(AuthController.LOGIN_INFO);
            if (null == loginInfo) {
                error(response, "未登录");
                return false;
            }
        } catch (Exception e) {
            error(response, e.getMessage());
            log.error("AuthInterceptor preHandle error", e);
            return false;
        }
        return true;
    }

    private void error(HttpServletResponse response, String message) {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out;
        try {
            ResponseResult data = new ResponseResult(ResponseResult.NOT_LOGIN, message, ResponseResult.ACTION_TOAST);
            out = response.getWriter();
            out.write(JsonUtils.toJSONString(data));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
