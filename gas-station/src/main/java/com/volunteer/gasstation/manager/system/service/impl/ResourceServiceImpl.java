package com.volunteer.gasstation.manager.system.service.impl;

import com.volunteer.gasstation.manager.system.entity.Resource;
import com.volunteer.gasstation.manager.system.mapper.ResourceMapper;
import com.volunteer.gasstation.manager.system.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huoyao
 * @since 2021-08-12
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
