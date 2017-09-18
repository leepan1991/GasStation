package com.volunteer.api.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.service.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseController<T> {
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

//    protected String getLoginCustomerId(HttpSession session) {
//        String customerId = StringUtils.EMPTY;
//        Object object = session.getAttribute(AclFilter.LOGIN_CUSTOMER);
//        if (object != null) {
//            customerId = object.toString();
//        }
//        return customerId;
//    }

//    protected Customer getLoginCustomer(HttpSession session) {
//        Object object = session.getAttribute(AclFilter.LOGIN_CUSTOMER);
//        Customer model = null;
//        if (object != null) {
//            try {
//                model = (Customer) object;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return model;
//    }

//    protected void setLoginCustomer(HttpSession session, Customer customer) {
//        session.setAttribute(AclFilter.LOGIN_CUSTOMER, customer);
//    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, T entity) {
        return new ResponseData("OK", this.getAbstractService().listPaged(
                parameter, entity));
    }
    @ResponseBody
    @RequestMapping(value = "listAll")
    public ResponseData listAll() {
        return new ResponseData("OK", this.getAbstractService().listAll());
    }
    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(int id) {
        this.getAbstractService().deleteByIds(new int[] { id });
        return this.response("删除成功", ResponseData.ACTION_TOAST);
    }
}
