package com.volunteer.gasstation.manager.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.system.converter.ResourceConverter;
import com.volunteer.gasstation.manager.system.dto.ResourceDTO;
import com.volunteer.gasstation.manager.system.entity.Resource;
import com.volunteer.gasstation.manager.system.entity.User;
import com.volunteer.gasstation.manager.system.service.IResourceService;
import com.volunteer.gasstation.utils.ResourceUtil;
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
        LambdaQueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>().lambda();
        queryWrapper.orderByAsc(Resource::getSequence);
        List<ResourceDTO> resourceList = ResourceConverter.INSTANCE.mapList(resourceService.list(queryWrapper));
        return new ResponseResult(ResourceUtil.loop(resourceList));
    }

}

