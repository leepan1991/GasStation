package com.volunteer.gasstation.manager.biz.dto;

import lombok.Data;

/**
 * @author by huoyao on 2021/8/12.
 */
@Data
public class EmployeeDTO {
    private Long id;

    private String name;

    private String phone;

    private String password;

    private String createTime;
}
