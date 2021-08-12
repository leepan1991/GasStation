package com.volunteer.gasstation.manager.biz.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.PageResult;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.biz.converter.EmployeeConverter;
import com.volunteer.gasstation.manager.biz.dto.EmployeeDTO;
import com.volunteer.gasstation.manager.biz.dto.EmployeeListRequestDTO;
import com.volunteer.gasstation.manager.biz.entity.Employee;
import com.volunteer.gasstation.manager.biz.enums.EmployeeStatusEnum;
import com.volunteer.gasstation.manager.biz.service.IEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huoyao
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/mgr/biz/employee")
public class EmployeeController extends BaseController {

    private IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseResult<List<EmployeeDTO>> list(EmployeeListRequestDTO req) {
        LambdaQueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>().lambda();
        queryWrapper.like(StringUtils.isNotBlank(req.getName()), Employee::getName, req.getName());
        queryWrapper.eq(StringUtils.isNotBlank(req.getPhone()), Employee::getPhone, req.getPhone());
        queryWrapper.eq(Employee::getStatus, EmployeeStatusEnum.NORMAL.intValue());
        Page<Employee> page = employeeService.page(req.convertMyBatisPlusPage(), queryWrapper);
        return new ResponseResult(PageResult.convert(page, EmployeeConverter.INSTANCE.mapList(page.getRecords())));
    }

    @PostMapping
    public ResponseResult save(@RequestBody EmployeeDTO record) {
        Employee employee = EmployeeConverter.INSTANCE.map(record);
        employee.setStatus(EmployeeStatusEnum.NORMAL.intValue());
        employee.setCreateTime(LocalDateTime.now());
        employeeService.save(employee);
        return new ResponseResult("添加成功", ResponseResult.ACTION_TOAST);
    }

    @PutMapping(value = "{id}")
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody EmployeeDTO record) {
        Employee employee = EmployeeConverter.INSTANCE.map(record);
        employee.setId(id);
        employeeService.updateById(employee);
        return new ResponseResult("更新成功", ResponseResult.ACTION_TOAST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(EmployeeStatusEnum.DELETED.intValue());
        employeeService.updateById(employee);
        return new ResponseResult("删除成功", ResponseResult.ACTION_TOAST);
    }
}

