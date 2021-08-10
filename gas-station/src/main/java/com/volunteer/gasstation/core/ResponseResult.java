package com.volunteer.gasstation.core;

import lombok.Data;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
public class ResponseResult<T> {
    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = -1;
    public static final Integer NOT_LOGIN = 401;
    public static final String ACTION_TOAST = "toast";
    public static final String ACTION_DIALOG = "dialog";

    public ResponseResult(int code, String msg, String action) {
        this.code = code;
        this.msg = msg;
        this.action = action;
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String msg, String action) {
        this.code = 0;
        this.msg = msg;
        this.action = action;
    }

    public ResponseResult(String msg) {
        this.code = 0;
        this.msg = msg;
    }

    public ResponseResult(T data) {
        this.code = 0;
        this.data = data;
    }

    private int code;
    private String msg;
    private String action;
    private T data;
}
