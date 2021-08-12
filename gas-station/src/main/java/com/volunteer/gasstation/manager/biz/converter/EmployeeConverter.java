package com.volunteer.gasstation.manager.biz.converter;

import com.volunteer.gasstation.manager.biz.dto.EmployeeDTO;
import com.volunteer.gasstation.manager.biz.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author by huoyao on 2021/8/12.
 */
@Mapper
public interface EmployeeConverter {
    EmployeeConverter INSTANCE = Mappers.getMapper(EmployeeConverter.class);

    /**
     * Employee转EmployeeDTO
     *
     * @param record
     * @return
     */
    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    EmployeeDTO map(Employee record);

    /**
     * EmployeeDTO转Employee
     *
     * @param user
     * @return
     */
    Employee map(EmployeeDTO user);

    /**
     * Employee转EmployeeDTO
     *
     * @param list
     * @return
     */
    List<EmployeeDTO> mapList(List<Employee> list);
}
