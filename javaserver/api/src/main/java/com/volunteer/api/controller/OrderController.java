package com.volunteer.api.controller;

import com.volunteer.api.web.SecurityUtil;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.GasBottleDTO;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.dto.OrderBottleDTO;
import com.volunteer.pojo.po.OrderBottle;
import com.volunteer.service.GasBottleService;
import com.volunteer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public ResponseData list() {
        LoginInfo loginInfo = SecurityUtil.getValue();
        return ResponseData.success("OK", orderService.listAvailable(loginInfo.getOrg().getId()));
    }

    @ResponseBody
    @RequestMapping("record")
    public ResponseData record(OrderBottleDTO orderBottle) {
        LoginInfo loginInfo = SecurityUtil.getValue();
        orderBottle.setOrgId(loginInfo.getOrg().getId());
        return ResponseData.success("OK", orderService.record(orderBottle));
    }

    @ResponseBody
    @RequestMapping("delivery")
    public ResponseData delivery(OrderBottleDTO orderBottle) {
        LoginInfo loginInfo = SecurityUtil.getValue();
        orderBottle.setOrgId(loginInfo.getOrg().getId());
        return ResponseData.success("OK", orderService.delivery(orderBottle));
    }

    @ResponseBody
    @RequestMapping("storage")
    public ResponseData storage(OrderBottleDTO orderBottle) {
        LoginInfo loginInfo = SecurityUtil.getValue();
        orderBottle.setOrgId(loginInfo.getOrg().getId());
        return ResponseData.success("OK", orderService.storage(orderBottle));
    }
}
