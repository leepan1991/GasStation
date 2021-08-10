package com.volunteer.gasstation.manager.biz.converter;

import com.volunteer.gasstation.manager.biz.dto.GasBottleDTO;
import com.volunteer.gasstation.manager.biz.entity.GasBottle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/9.
 */
@Mapper
public interface GasBottleConverter {
    GasBottleConverter INSTANCE = Mappers.getMapper(GasBottleConverter.class);

    /**
     * GasBottle转GasBottleDTO
     *
     * @param record
     * @return
     */
    @Mappings({
            @Mapping(source = "ccrq", target = "ccrq", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "jyrqf", target = "jyrqf", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "jyrqn", target = "jyrqn", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    GasBottleDTO map(GasBottle record);

    /**
     * GasBottleDTO转GasBottle
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(source = "ccrq", target = "ccrq", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "jyrqf", target = "jyrqf", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "jyrqn", target = "jyrqn", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    GasBottle map(GasBottleDTO user);

    /**
     * GasBottle转GasBottleDTO
     *
     * @param list
     * @return
     */
    List<GasBottleDTO> mapList(List<GasBottle> list);
}
