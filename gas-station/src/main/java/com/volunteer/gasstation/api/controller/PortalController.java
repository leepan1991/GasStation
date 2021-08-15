package com.volunteer.gasstation.api.controller;

import com.volunteer.gasstation.manager.biz.entity.PortalConfig;
import com.volunteer.gasstation.manager.biz.service.IPortalConfigService;
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
    }

    @RequestMapping
    public String index(Model model) {
        List<PortalConfig> portalConfigList = portalConfigService.list();
        for (PortalConfig config : portalConfigList) {
            if (StringUtils.equalsIgnoreCase(config.getCode(), "qiye")) {
                model.addAttribute("qiye", config.getContent());
            } else if (StringUtils.equalsIgnoreCase(config.getCode(), "anquan")) {
                model.addAttribute("anquan", config.getContent());
            }
        }
        return "index";
    }
}
