package com.volunteer.gasstation.core.interceptor;

import com.volunteer.gasstation.manager.system.controller.AuthController;
import com.volunteer.gasstation.manager.system.dto.LoginInfoDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by huoyao on 2021/8/8.
 */
@Slf4j
public class MgrAuthInterceptor extends BaseAuthInterceptor {
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
            log.error("MgrAuthInterceptor preHandle error", e);
            return false;
        }
        return true;
    }
}
