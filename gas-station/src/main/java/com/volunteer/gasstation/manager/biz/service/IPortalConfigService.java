package com.volunteer.gasstation.manager.biz.service;

import com.volunteer.gasstation.manager.biz.dto.PortalConfigDTO;
import com.volunteer.gasstation.manager.biz.entity.PortalConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huoyao
 * @since 2021-08-15
 */
public interface IPortalConfigService extends IService<PortalConfig> {

    /**
     * 缓存
     * @return
     */
    PortalConfigDTO cache();
}
