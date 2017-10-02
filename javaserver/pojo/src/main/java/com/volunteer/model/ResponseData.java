package com.volunteer.model;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class ResponseData {

	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = 1;
	public static final int CODE_NOT_LOGIN = -1;
	public static final int CODE_LOGIN_NEED = 40000;

	public static final String ACTION_TOAST = "toast";
	public static final String ACTION_ALERT = "alert";

	public int code = CODE_SUCCESS;
	public String message = "";
	public String action = "";
	public Object data;

	public ResponseData(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public ResponseData(String message, String action) {
		super();
		this.message = message;
		this.action = action;
	}

	public ResponseData(String message, String action, Object data) {
		super();
		this.message = message;
		this.action = action;
		this.data = data;
	}

	public ResponseData(int code, String message, String action, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.action = action;
		this.data = data;
	}

	public ResponseData(int code, String message, String action) {
		super();
		this.code = code;
		this.message = message;
		this.action = action;
	}

	public ResponseData() {
	}

	public static ResponseData success(String message, Object data) {
		return new ResponseData(message, data);
	}

	public static ResponseData success(String message) {
		return new ResponseData(message, ResponseData.ACTION_TOAST);
	}

	public static ResponseData error(String message) {
		return new ResponseData(ResponseData.CODE_ERROR, message, ResponseData.ACTION_TOAST);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"code\":").append(code);
		sb.append(",\"action\":").append('"').append(action).append('"');
		sb.append(",\"message\":").append('"').append(message).append('"');
		sb.append(",\"data\":");

		if (data != null && this.data.toString().length() > 0) {
			sb.append(data);
		} else {
			sb.append("\"\"");
		}
		sb.append("}");
		return sb.toString();
	}

	public byte[] toUtf8Bytes(String callback) {
		try {
			if (StringUtils.isNotBlank(callback)) {
				String txt = String
						.format("%s(%s);", callback, this.toString());
				return txt.getBytes("UTF-8");
			} else {
				return this.toString().getBytes("UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] toUtf8Bytes() {
		try {
			return this.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
