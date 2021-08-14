package com.volunteer.gasstation.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.gasstation.core.TokenManager;
import com.volunteer.gasstation.api.dto.ApiLoginInfoDTO;
import com.volunteer.gasstation.api.dto.ApiLoginRequestDTO;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.biz.converter.EmployeeConverter;
import com.volunteer.gasstation.manager.biz.converter.GasMediumConverter;
import com.volunteer.gasstation.manager.biz.dto.EmployeeDTO;
import com.volunteer.gasstation.manager.biz.entity.Employee;
import com.volunteer.gasstation.manager.biz.service.IEmployeeService;
import com.volunteer.gasstation.manager.biz.service.IGasMediumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author by huoyao on 2021/8/14.
 */
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private TokenManager tokenManager;
    private IEmployeeService employeeService;
    private IGasMediumService gasMediumService;

    public ApiAuthController(TokenManager tokenManager, IEmployeeService employeeService, IGasMediumService gasMediumService) {
        this.tokenManager = tokenManager;
        this.employeeService = employeeService;
        this.gasMediumService = gasMediumService;
    }

    @PostMapping("login")
    public ResponseResult<ApiLoginInfoDTO> login(@RequestBody ApiLoginRequestDTO req) {
        LambdaQueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>().lambda();
        queryWrapper.eq(Employee::getPhone, req.getPhone());
        Employee employee = employeeService.getOne(queryWrapper);
        if (null == employee || !StringUtils.equals(req.getPassword(), employee.getPassword())) {
            return new ResponseResult(ResponseResult.ERROR, "账号或者密码错误", ResponseResult.ACTION_TOAST);
        }
        EmployeeDTO employeeDTO = EmployeeConverter.INSTANCE.map(employee);
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        ApiLoginInfoDTO loginInfo = new ApiLoginInfoDTO();
        loginInfo.setToken(token);
        loginInfo.setEmployee(employeeDTO);
        loginInfo.setGasMediumList(GasMediumConverter.INSTANCE.mapList(gasMediumService.list()));
        tokenManager.put(loginInfo);

        return new ResponseResult(loginInfo);
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public ResponseResult logout(@RequestHeader("token") String token) {
        tokenManager.remove(token);
        return new ResponseResult("退出成功");
    }
}
