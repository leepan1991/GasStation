package com.volunteer.gasstation.manager.system.converter;

import com.volunteer.gasstation.manager.system.dto.ResourceDTO;
import com.volunteer.gasstation.manager.system.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/8.
 */
@Mapper
public interface ResourceConverter {
    ResourceConverter INSTANCE = Mappers.getMapper(ResourceConverter.class);

    /**
     * ResourceDTO转Resource
     *
     * @param resource
     * @return
     */
    ResourceDTO map(Resource resource);

    /**
     * Resource转ResourceDTO
     *
     * @param list
     * @return
     */
    List<ResourceDTO> mapList(List<Resource> list);
}
