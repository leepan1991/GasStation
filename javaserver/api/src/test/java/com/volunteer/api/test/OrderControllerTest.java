package com.volunteer.api.test;

import com.alibaba.fastjson.JSON;
import com.volunteer.common.HttpUtil;
import com.volunteer.pojo.po.GasBottle;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
public class OrderControllerTest extends BaseTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login();
    }

    @Test
    public void list() throws Exception {
        System.out.println(HttpUtil.executeGet(String
                .format("%s/order/list?token=%s", URL_BASE, appLoginInfo.getToken())));
    }
}
