package com.volunteer.api.test;

import com.volunteer.common.HttpUtil;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
public class HomeControllerTest extends BaseTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login();
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println(HttpUtil.executeGet(String
                .format("%s/checkresult/findCheckResultWhenEdit?token=%s&checkCode=20170713201358erRtNX", URL_BASE, appLoginInfo.getToken())));
    }
}
