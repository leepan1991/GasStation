package com.volunteer.gasstation.api.dto;

import com.volunteer.gasstation.manager.biz.dto.EmployeeDTO;
import com.volunteer.gasstation.manager.biz.dto.GasMediumDTO;
import lombok.Data;

import java.util.List;

/**
 * @author by huoyao on 2021/8/14.
 */
@Data
public class ApiLoginInfoDTO {
    private String token;
    private EmployeeDTO employee;
    private List<GasMediumDTO> gasMediumList;
}
