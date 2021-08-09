package com.volunteer.gasstation.manager.system.dto;

import lombok.Data;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String roleIds;
    private Byte status;
    private String createTime;
}
