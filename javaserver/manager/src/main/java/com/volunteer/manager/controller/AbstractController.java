package com.volunteer.manager.controller;

import javax.servlet.http.HttpSession;

import com.volunteer.manager.web.AclFilter;
import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.TableParameter;
import com.volunteer.service.AbstractService;
import com.volunteer.model.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractController<T> {

	public abstract AbstractService<T> getAbstractService();

	protected ResponseData response(String message, Object data) {
		return new ResponseData(message, data);
	}

	protected ResponseData response(String message, String action, Object data) {
		return new ResponseData(message, action, data);
	}

	protected ResponseData response(String message, String action) {
		return new ResponseData(message, action);
	}

	protected ResponseData response(int code, String message, String action,
			Object data) {
		return new ResponseData(code, message, action, data);
	}

	protected ResponseData response(int code, String message, String action) {
		return new ResponseData(code, message, action);
	}

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

	@ResponseBody
	@RequestMapping(value = "delete")
	public ResponseData delete(String id) {
		this.getAbstractService().deleteByIds(new Object[] { id });
		return this.response("删除成功", ResponseData.ACTION_TOAST);
	}

	@ResponseBody
	@RequestMapping(value = "listPaged")
	public ResponseData listPaged(TableParameter parameter, T entity) {
		return new ResponseData("OK", this.getAbstractService().listPaged(
				parameter, entity));
	}

}
