package com.volunteer.gasstation.manager.system.converter;

import com.volunteer.gasstation.manager.system.dto.UserDTO;
import com.volunteer.gasstation.manager.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/8.
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * User转UserDTO
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    UserDTO map(User user);

    /**
     * UserDTO转User
     *
     * @param user
     * @return
     */
    User map(UserDTO user);

    /**
     * User转UserDTO
     *
     * @param list
     * @return
     */
    List<UserDTO> mapList(List<User> list);
}
