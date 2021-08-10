package com.volunteer.gasstation.manager.system.controller;


import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.ResourceConverter;
import com.volunteer.gasstation.manager.system.dto.ResourceDTO;
import com.volunteer.gasstation.manager.system.service.IResourceService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author huoyao
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/mgr/system/resource")
public class ResourceController extends BaseController {

    private final IResourceService resourceService;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseResult<List<ResourceDTO>> list() {
        List<ResourceDTO> resourceList = ResourceConverter.INSTANCE.mapList(resourceService.list());
        List<ResourceDTO> list = resourceList.stream().filter(m -> m.getParentId() == null).map(
                (m) -> {
                    m.setChildren(getChildren(m, resourceList));
                    return m;
                }
        ).collect(Collectors.toList());
        return new ResponseResult(list);
    }

    /**
     * 递归查询子节点
     * @param root  根节点
     * @param all   所有节点
     * @return 根节点信息
     */
    private static List<ResourceDTO> getChildren(ResourceDTO root, List<ResourceDTO> all) {
        List<ResourceDTO> children = all.stream().filter(m -> Objects.equals(m.getParentId(), root.getId())).map(
                (m) -> {
                    m.setChildren(getChildren(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        return children;
    }

}

