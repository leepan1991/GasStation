package com.volunteer.gasstation.core.interceptor;

import com.volunteer.gasstation.api.dto.ApiLoginInfoDTO;
import com.volunteer.gasstation.core.SecurityUtil;
import com.volunteer.gasstation.core.TokenManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by huoyao on 2021/8/14.
 */
@Slf4j
public class ApiAuthInterceptor extends BaseAuthInterceptor {

    @Resource
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        ApiLoginInfoDTO loginInfo = tokenManager.get(token);
        if (null == loginInfo) {
            error(response, "未登录");
            return false;
        }
        SecurityUtil.setValue(loginInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityUtil.remove();
    }
}
