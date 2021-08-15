package com.volunteer.gasstation.api.controller;

import com.volunteer.gasstation.api.dto.ApiLoginInfoDTO;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.core.SecurityUtil;
import com.volunteer.gasstation.manager.biz.converter.GasBottleConverter;
import com.volunteer.gasstation.manager.biz.dto.GasBottleDTO;
import com.volunteer.gasstation.manager.biz.entity.GasBottle;
import com.volunteer.gasstation.manager.biz.service.IGasBottleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author by huoyao on 2021/8/12.
 */
@RestController
@RequestMapping("/api/gasBottle")
public class ApiGasBottleController {

    private IGasBottleService gasBottleService;

    public ApiGasBottleController(IGasBottleService gasBottleService) {
        this.gasBottleService = gasBottleService;
    }

    @PostMapping
    public ResponseResult save(@RequestBody GasBottleDTO record) {
        ApiLoginInfoDTO loginInfo = SecurityUtil.getValue();
        GasBottle gasBottle = GasBottleConverter.INSTANCE.map(record);
        gasBottle.setDeleted(Boolean.FALSE);
        gasBottle.setCreateTime(LocalDateTime.now());
        gasBottle.setEmployeeId(loginInfo.getEmployee().getId());
        gasBottleService.saveOrUpdate(gasBottle);
        return new ResponseResult("添加成功", ResponseResult.ACTION_TOAST);
    }

    @PostMapping("log")
    public ResponseResult log() {
        return null;
    }
}
