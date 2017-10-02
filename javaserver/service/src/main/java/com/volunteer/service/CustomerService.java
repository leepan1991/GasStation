package com.volunteer.service;

import com.volunteer.dao.mapper.CustomerMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class CustomerService extends AbstractService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Customer entity) {
        return customerMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Customer entity) {
        return customerMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        customerMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Customer entity) {
        TableData tableData = new TableData();
        tableData.data = this.customerMapper.listPaged(parameter, entity);
        parameter.setTotal(this.customerMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }
}
