package com.volunteer.gasstation.manager.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author by huoyao on 2021/8/10.
 */
@Data
public class ResourceDTO {

    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private List<ResourceDTO> children;
    private Boolean selected;
}
