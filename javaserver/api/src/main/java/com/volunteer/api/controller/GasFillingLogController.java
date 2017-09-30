package com.volunteer.api.controller;

import com.volunteer.api.web.SecurityUtil;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.GasFillingLog;
import com.volunteer.service.GasBottleService;
import com.volunteer.service.GasFillingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
@Controller
@RequestMapping("gasFillingLog")
public class GasFillingLogController extends BaseController {

    @Autowired
    private GasFillingLogService gasFillingLogService;

    /**
     * 充气
     * @return
     */
    @ResponseBody
    @RequestMapping("create")
    public ResponseData create(@RequestBody GasFillingLog gasFillingLog) {
        LoginInfo loginInfo = SecurityUtil.getValue();
        gasFillingLog.setFillinger(loginInfo.getEmployee().getId());
        gasFillingLog.setOrgId(loginInfo.getOrg().getId());
        gasFillingLogService.insert(gasFillingLog);
        return new ResponseData();
    }

}
