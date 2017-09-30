package com.volunteer.api.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.service.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseController {

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
}
