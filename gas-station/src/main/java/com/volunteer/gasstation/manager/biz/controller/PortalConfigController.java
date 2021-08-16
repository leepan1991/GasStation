package com.volunteer.gasstation.manager.biz.controller;

import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.biz.dto.PortalConfigDTO;
import com.volunteer.gasstation.manager.biz.entity.PortalConfig;
import com.volunteer.gasstation.manager.biz.service.IPortalConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by huoyao on 2021/8/15.
 */
@RestController
@RequestMapping("/mgr/biz/portalConfig")
public class PortalConfigController {

    private IPortalConfigService portalConfigService;

    public PortalConfigController(IPortalConfigService portalConfigService) {
        this.portalConfigService = portalConfigService;
    }

    @GetMapping
    public ResponseResult<PortalConfigDTO> get() {
        List<PortalConfig> configList = portalConfigService.list();

        PortalConfigDTO result = new PortalConfigDTO();
        for (PortalConfig config : configList) {
            if (StringUtils.equalsIgnoreCase(config.getCode(), "qiye")) {
                result.setQiye(config.getContent());
            } else if (StringUtils.equalsIgnoreCase(config.getCode(), "anquan")) {
                result.setAnquan(config.getContent());
            }
        }

        return new ResponseResult(result);
    }

    @PostMapping("")
    public ResponseResult save(@RequestBody PortalConfigDTO record) {
        PortalConfig qiye = new PortalConfig();
        qiye.setCode("qiye");
        qiye.setContent(record.getQiye());
        portalConfigService.updateById(qiye);

        PortalConfig anquan = new PortalConfig();
        anquan.setCode("anquan");
        anquan.setContent(record.getAnquan());
        portalConfigService.updateById(anquan);

        portalConfigService.cache();
        return new ResponseResult("保存成功", ResponseResult.ACTION_TOAST);
    }
}
