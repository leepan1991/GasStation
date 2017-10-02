package com.volunteer.api.util;

import com.volunteer.api.controller.BaseController;
import com.volunteer.api.web.SecurityUtil;
import com.volunteer.model.ResponseData;
import com.volunteer.pojo.dto.GasBottleDTO;
import com.volunteer.pojo.dto.LoginInfo;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.Order;
import com.volunteer.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class GasBottleControllerUtil {

    public static void verify(GasBottleDTO gasBottleDTO) {
        if (null == gasBottleDTO || StringUtils.isBlank(gasBottleDTO.getOrderId())) {
            throw new RuntimeException("订单号不能为空");
        }
    }
}
