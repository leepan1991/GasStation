package com.volunteer.api.web;

import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.service.token.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class SecurityServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		SecurityUtil.remove();
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		TokenManager tokenManager = appContext.getBean(TokenManager.class);
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		String token = request.getHeader("token");
		if (StringUtils.isBlank(token)) {
			token = request.getParameter("token");
		}
		if (StringUtils.isNotBlank(token)) {
			LoginInfo loginInfo = tokenManager.get(token);
			if (null != loginInfo) {
				SecurityUtil.setValue(loginInfo);
			}
		}
	}
}
