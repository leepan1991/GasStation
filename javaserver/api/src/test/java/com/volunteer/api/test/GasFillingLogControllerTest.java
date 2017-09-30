package com.volunteer.api.test;

import com.alibaba.fastjson.JSON;
import com.volunteer.common.HttpUtil;
import com.volunteer.pojo.po.GasFillingLog;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
public class GasFillingLogControllerTest extends BaseTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login();
    }

    @Test
    public void create() throws Exception {
        GasFillingLog log = new GasFillingLog();
        log.setCode("000001");
        log.setInspector("李攀");
        log.setLatitude(0.2);
        log.setLongitude(0.2);
        HttpUtil.postJSON(String
                .format("%s/gasFillingLog/create?token=%s", URL_BASE, appLoginInfo.getToken()), JSON.toJSONString(log));
    }
}
