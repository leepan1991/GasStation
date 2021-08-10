package com.volunteer.gasstation.manager.biz.dto;

import com.volunteer.gasstation.core.PageRequest;
import com.volunteer.gasstation.manager.biz.entity.GasBottle;
import lombok.Data;

/**
 * @author by huoyao on 2021/8/9.
 */
@Data
public class GasBottleListRequestDTO extends PageRequest<GasBottle> {

    /**
     * 气瓶编号(自编号)[1]QPNO
     */
    private String qpno;
}
