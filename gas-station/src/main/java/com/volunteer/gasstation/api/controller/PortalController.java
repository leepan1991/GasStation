package com.volunteer.gasstation.api.controller;

import com.volunteer.gasstation.manager.biz.dto.PortalConfigDTO;
import com.volunteer.gasstation.manager.biz.entity.PortalConfig;
import com.volunteer.gasstation.manager.biz.service.IPortalConfigService;
import com.volunteer.gasstation.manager.biz.service.impl.PortalConfigServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author by huoyao on 2021/8/12.
 */
@Controller
@RequestMapping("/api/portal")
public class PortalController {

    private IPortalConfigService portalConfigService;

    public PortalController(IPortalConfigService portalConfigService) {
        this.portalConfigService = portalConfigService;
        portalConfigService.cache();
    }

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("qiye", PortalConfigServiceImpl.portalConfigCache.getQiye());
        model.addAttribute("anquan", PortalConfigServiceImpl.portalConfigCache.getAnquan());
        return "index";
    }
}
