package com.volunteer.gasstation.manager.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
@Accessors(chain = true)
public class LoginInfoDTO {

    private Long id;
    private String name;
    private String username;
}