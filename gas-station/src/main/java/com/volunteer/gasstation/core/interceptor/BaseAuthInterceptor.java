package com.volunteer.gasstation.core.interceptor;

import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.utils.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author by huoyao on 2021/8/14.
 */
public abstract class BaseAuthInterceptor implements HandlerInterceptor {
    protected void error(HttpServletResponse response, String message) {
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
