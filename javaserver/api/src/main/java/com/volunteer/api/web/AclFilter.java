package com.volunteer.api.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@WebFilter(filterName = "aclFilter", urlPatterns = "/*")
public class AclFilter implements Filter {

    public static final String LOGIN_CUSTOMER = "loginCustomer";
    private static String[] WHITE_LIST = new String[]{
            "/api/login", "/api/userInfo"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();

//        Customer customer = (Customer) session.getAttribute(LOGIN_CUSTOMER);
//        if (null == customer && !ArrayUtils.contains(WHITE_LIST, uri)) {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().write(JSON.toJSONString(new ResponseData(ResponseData.CODE_LOGIN_NEED, "未登录", ResponseData.ACTION_TOAST)));
//            return;
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
