package com.volunteer.gasstation.manager.biz.service.impl;

import com.volunteer.gasstation.manager.biz.entity.Employee;
import com.volunteer.gasstation.manager.biz.mapper.EmployeeMapper;
import com.volunteer.gasstation.manager.biz.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huoyao
 * @since 2021-08-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
