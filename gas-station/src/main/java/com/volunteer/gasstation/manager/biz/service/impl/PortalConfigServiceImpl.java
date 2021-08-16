package com.volunteer.gasstation.manager.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.volunteer.gasstation.manager.biz.dto.PortalConfigDTO;
import com.volunteer.gasstation.manager.biz.entity.PortalConfig;
import com.volunteer.gasstation.manager.biz.mapper.PortalConfigMapper;
import com.volunteer.gasstation.manager.biz.service.IPortalConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huoyao
 * @since 2021-08-15
 */
@Service
public class PortalConfigServiceImpl extends ServiceImpl<PortalConfigMapper, PortalConfig> implements IPortalConfigService {

    public static PortalConfigDTO portalConfigCache = new PortalConfigDTO();

    @Override
    public synchronized PortalConfigDTO cache() {
        List<PortalConfig> portalConfigList = this.baseMapper.selectList(Wrappers.emptyWrapper());
        for (PortalConfig config : portalConfigList) {
            if (StringUtils.equalsIgnoreCase(config.getCode(), "qiye")) {
                portalConfigCache.setQiye(config.getContent());
            } else if (StringUtils.equalsIgnoreCase(config.getCode(), "anquan")) {
                portalConfigCache.setAnquan(config.getContent());
            }
        }
        return portalConfigCache;
    }
}
