package com.volunteer.gasstation.api.dto;

import lombok.Data;

/**
 * @author by huoyao on 2021/8/14.
 */
@Data
public class ApiLoginRequestDTO {

    private String phone;
    private String password;
}
