package com.volunteer.manager.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.pojo.Customer;
import com.volunteer.service.AbstractService;
import com.volunteer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends AbstractController<Customer> {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Customer customer) {
        this.getAbstractService().insert(customer);
        return this.response("添加成功", ResponseData.ACTION_TOAST);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Customer customer) {
        this.getAbstractService().update(customer);
        return this.response("修改成功", ResponseData.ACTION_TOAST);
    }

    @Override
    public AbstractService<Customer> getAbstractService() {
        return customerService;
    }
}
