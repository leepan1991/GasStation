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
public class GasBottleControllerTest extends BaseTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login();
    }

    @Test
    public void create() throws Exception {
        GasBottle gasBottle = new GasBottle();
        gasBottle.setLongitude(0.1);
        gasBottle.setLatitude(0.1);
        gasBottle.setCode("000004");
        gasBottle.setBottleCode("000004");
        gasBottle.setDeviceName("设备名称");
        gasBottle.setInvalidatedDate(new Date());
        gasBottle.setLastCheckTime(new Date());
        gasBottle.setManufactureDate(new Date());
        gasBottle.setManufacturer("制造单位");
        gasBottle.setMediumId(1);
        gasBottle.setMediumName("工业氧气");
        gasBottle.setNextCheckTime(new Date());
        gasBottle.setVolume("容积");
        gasBottle.setWallThickness("壁厚");
        gasBottle.setWeight("瓶重");
        gasBottle.setWorkPressure("压强");
        HttpUtil.postJSON(String
                .format("%s/gasBottle/create?token=%s", URL_BASE, appLoginInfo.getToken()), JSON.toJSONString(gasBottle));
    }
}
