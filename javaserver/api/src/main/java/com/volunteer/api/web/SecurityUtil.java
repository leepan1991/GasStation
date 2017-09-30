package com.volunteer.api.web;

import com.volunteer.pojo.dto.LoginInfo;

public class SecurityUtil {

	private static final ThreadLocal<LoginInfo> loginInfoCache = new ThreadLocal<LoginInfo>();

	public static void setValue(LoginInfo loginInfo) {
		loginInfoCache.set(loginInfo);
	}
	
	public static LoginInfo getValue() {
		return loginInfoCache.get();
	}
	
	public static void remove() {
		loginInfoCache.remove();
	}
}
