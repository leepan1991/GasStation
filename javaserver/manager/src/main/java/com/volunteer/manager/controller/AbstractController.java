package com.volunteer.manager.controller;

import javax.servlet.http.HttpSession;

import com.volunteer.manager.web.AclFilter;
import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.TableParameter;
import com.volunteer.service.AbstractService;
import com.volunteer.model.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractController {

	protected int getLoginUserId(HttpSession session) {
		int userId = 0;
		Object object = session.getAttribute(AclFilter.LOGIN_USER);
		ManageLoginUser user = null;
		if (object != null) {
			try {
				user = (ManageLoginUser) object;
				userId = user.getUser().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userId;
	}

	protected ManageLoginUser getLoginUser(HttpSession session) {
		Object object = session.getAttribute(AclFilter.LOGIN_USER);
		ManageLoginUser user = null;
		if (object != null) {
			try {
				user = (ManageLoginUser) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
