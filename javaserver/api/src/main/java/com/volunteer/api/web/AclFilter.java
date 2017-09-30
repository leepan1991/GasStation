package com.volunteer.api.web;

import com.alibaba.fastjson.JSON;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.LoginInfo;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@WebFilter(filterName = "aclFilter", urlPatterns = "/*")
public class AclFilter implements Filter {

    private static String[] WHITE_LIST = new String[]{"/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        try {
            if (!ArrayUtils.contains(WHITE_LIST, path)) {
                LoginInfo loginInfo = SecurityUtil.getValue();
                if (null == loginInfo) {
                    error(response, "未登录");
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            error(response, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {}

    private void error(HttpServletResponse response, String message) {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out;
        try {
            ResponseData data = new ResponseData(ResponseData.CODE_ERROR, message, ResponseData.ACTION_TOAST);
            out = response.getWriter();
            out.write(JSON.toJSONString(data));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
