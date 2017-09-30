package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.dao.mapper.CustomerMapper;
import com.volunteer.dao.mapper.EmployeeMapper;
import com.volunteer.dao.mapper.MediumMapper;
import com.volunteer.dao.mapper.OrgMapper;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.Customer;
import com.volunteer.pojo.po.Employee;
import com.volunteer.pojo.po.Medium;
import com.volunteer.pojo.po.Org;
import com.volunteer.service.token.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class EmployeeService extends AbstractService<Employee> {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private MediumMapper mediumMapper;

    public EmployeeService() {
        super(Employee.class);
    }

    public LoginInfo login(Employee employee) {
        Employee e = employeeMapper.findByPhone(employee.getPhone());
        if (StringUtils.equals(e.getPassword(), employee.getPassword())) {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            Org org = orgMapper.getById(e.getOrgId());
            List<Medium> mediumList = mediumMapper.listByOrgId(e.getOrgId());
            LoginInfo loginInfo = new LoginInfo(token, e, org, mediumList);
            tokenManager.put(loginInfo);
            return loginInfo;
        }
        return null;
    }

    public boolean logout(String token) {
        tokenManager.remove(token);
        return true;
    }

    @Override
    public AbstractMapper getAbstractMapper() {
        return this.employeeMapper;
    }
}
