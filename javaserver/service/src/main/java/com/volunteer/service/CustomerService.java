package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.CustomerMapper;
import com.volunteer.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class CustomerService extends AbstractService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.customerMapper;
    }
}
