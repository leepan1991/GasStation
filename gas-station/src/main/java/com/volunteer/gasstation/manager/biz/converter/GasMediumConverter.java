package com.volunteer.gasstation.manager.biz.converter;

import com.volunteer.gasstation.manager.biz.dto.GasMediumDTO;
import com.volunteer.gasstation.manager.biz.entity.GasMedium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/12.
 */
@Mapper
public interface GasMediumConverter {
    GasMediumConverter INSTANCE = Mappers.getMapper(GasMediumConverter.class);

    /**
     * GasMedium转GasMediumDTO
     *
     * @param record
     * @return
     */
    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    GasMediumDTO map(GasMedium record);

    /**
     * GasMediumDTO转GasMedium
     *
     * @param user
     * @return
     */
    GasMedium map(GasMediumDTO user);

    /**
     * GasMedium转GasMediumDTO
     *
     * @param list
     * @return
     */
    List<GasMediumDTO> mapList(List<GasMedium> list);
}
