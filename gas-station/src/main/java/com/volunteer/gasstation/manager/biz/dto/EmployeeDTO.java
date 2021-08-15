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
    /**
     * 1充装员 2质检员 3admin
     */
    private Integer type;
    private String createTime;
}
