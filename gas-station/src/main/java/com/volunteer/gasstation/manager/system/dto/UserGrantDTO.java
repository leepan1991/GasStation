package com.volunteer.gasstation.manager.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author by huoyao on 2021/8/10.
 */
@Data
public class UserGrantDTO {
    private List<Long> roleIdList;
}
