package com.volunteer.gasstation.manager.system.dto;

import com.volunteer.gasstation.core.PageRequest;
import com.volunteer.gasstation.manager.system.entity.User;
import lombok.Data;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
public class UserListRequestDTO extends PageRequest<User> {

    private String name;
    private String username;
}
