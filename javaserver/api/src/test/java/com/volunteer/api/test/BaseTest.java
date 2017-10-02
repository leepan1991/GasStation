package com.volunteer.api.test;

import com.alibaba.fastjson.JSON;
import com.volunteer.common.HttpUtil;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.Employee;

public class BaseTest {

	// private static final String PHONE = "18080961548";
	private static final String PHONE = "15828059647";
	private static final String PASSWORD = "123456";
	protected static final String URL_BASE = "http://localhost:8080/api";
	protected static LoginInfo appLoginInfo;

	protected static void login() throws Exception {
		ResponseData result = HttpUtil.executeGet(String.format("%s/login?phone=%s&password=%s", URL_BASE, PHONE, PASSWORD),
				ResponseData.class);
//		Employee employee = new Employee();
//		HttpUtil.postJSON(String.format("%s/login?phone=%s&password=%s", URL_BASE, PHONE, PASSWORD), JSON.toJSONString(employee))
		String resultObject = result.data.toString();
		appLoginInfo = JSON.parseObject(resultObject, LoginInfo.class);
		System.out.println(appLoginInfo.getToken());
	}

	protected void postJSON(String url, Object entity) throws Exception {
		System.out.println(HttpUtil.postJSON(appendToken(url), JSON.toJSONString(entity)));
	}

	protected String get(String url) throws Exception {
		String result = HttpUtil.executeGet(appendToken(url));
		System.out.println(result);
		return result;
	}

	protected <T> T getObject(String url, Class<T> clz) throws Exception {
		String result = get(url);
		return JSON.parseObject(result, clz);
	}

	private static String appendToken(String url) {
		if (url.contains("_token")) {
			return url;
		}
		if (url.contains("?")) {
			return url + "&_token=" + appLoginInfo.getToken();
		}
		return url + "?_token=" + appLoginInfo.getToken();

	}
}
