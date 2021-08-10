package com.volunteer.gasstation.manager.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.PageResult;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.biz.converter.GasBottleConverter;
import com.volunteer.gasstation.manager.biz.dto.GasBottleDTO;
import com.volunteer.gasstation.manager.biz.dto.GasBottleListRequestDTO;
import com.volunteer.gasstation.manager.biz.entity.GasBottle;
import com.volunteer.gasstation.manager.biz.service.IGasBottleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author by huoyao on 2021/8/9.
 */
@RestController
@RequestMapping("/mgr/biz/gasBottle")
public class GasBottleController extends BaseController {

    private IGasBottleService gasBottleService;

    public GasBottleController(IGasBottleService gasBottleService) {
        this.gasBottleService = gasBottleService;
    }

    @GetMapping
    public ResponseResult<PageResult<GasBottleDTO>> list(GasBottleListRequestDTO req) {
        LambdaQueryWrapper<GasBottle> queryWrapper = new QueryWrapper<GasBottle>().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(req.getQpno()), GasBottle::getQpno, req.getQpno());
        queryWrapper.eq(GasBottle::getDeleted, Boolean.FALSE);
        Page<GasBottle> page = gasBottleService.page(req.convertMyBatisPlusPage(), queryWrapper);
        return new ResponseResult(PageResult.convert(page, GasBottleConverter.INSTANCE.mapList(page.getRecords())));
    }

    @GetMapping(value = "{id}")
    public ResponseResult<GasBottleDTO> getById(@PathVariable("id") Long id) {
        GasBottle gasBottle = gasBottleService.getById(id);
        return new ResponseResult(GasBottleConverter.INSTANCE.map(gasBottle));
    }

    @PutMapping(value = "{id}")
    public ResponseResult<GasBottleDTO> updateById(@PathVariable("id") Long id, @RequestBody GasBottleDTO record) {
        record.setId(id);
        GasBottle gasBottle = GasBottleConverter.INSTANCE.map(record);
        gasBottleService.updateById(gasBottle);
        return new ResponseResult("更新成功", ResponseResult.ACTION_TOAST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult<GasBottleDTO> deleteById(@PathVariable("id") Long id) {
        GasBottle gasBottle = new GasBottle();
        gasBottle.setId(id);
        gasBottle.setDeleted(Boolean.TRUE);
        gasBottleService.updateById(gasBottle);
        return new ResponseResult("删除成功", ResponseResult.ACTION_TOAST);
    }
}
