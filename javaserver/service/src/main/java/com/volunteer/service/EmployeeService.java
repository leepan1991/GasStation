package com.volunteer.service;

import com.volunteer.dao.mapper.EmployeeMapper;
import com.volunteer.dao.mapper.MediumMapper;
import com.volunteer.dao.mapper.OrgMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.dto.LoginInfo;
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
public class EmployeeService extends AbstractService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private MediumMapper mediumMapper;

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

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Employee entity) {
        return employeeMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Employee entity) {
        return employeeMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        employeeMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Employee entity) {
        TableData tableData = new TableData();
        tableData.data = this.employeeMapper.listPaged(parameter, entity);
        parameter.setTotal(this.employeeMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }
}
