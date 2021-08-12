package com.volunteer.gasstation.manager.biz.dto;

import com.volunteer.gasstation.core.PageRequest;
import com.volunteer.gasstation.manager.biz.entity.Employee;
import lombok.Data;

/**
 * @author by huoyao on 2021/8/12.
 */
@Data
public class EmployeeListRequestDTO extends PageRequest<Employee> {

    private String name;
    private String phone;
}
