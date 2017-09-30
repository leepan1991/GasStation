package com.volunteer.api.controller;

import com.volunteer.api.web.SecurityUtil;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.service.GasBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
@Controller
@RequestMapping("gasBottle")
public class GasBottleController extends BaseController {

    @Autowired
    private GasBottleService gasBottleService;

    /**
     * 创建
     * @return
     */
    @ResponseBody
    @RequestMapping("create")
    public ResponseData create(@RequestBody GasBottle gasBottle) {
        LoginInfo loginInfo = SecurityUtil.getValue();
        gasBottle.setOrgId(loginInfo.getOrg().getId());
        this.gasBottleService.insert(gasBottle);
        return new ResponseData();
    }

}
