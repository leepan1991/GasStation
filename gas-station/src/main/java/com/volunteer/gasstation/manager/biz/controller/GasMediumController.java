package com.volunteer.gasstation.manager.biz.controller;


import com.volunteer.gasstation.core.BaseController;
import com.volunteer.gasstation.core.ResponseResult;
import com.volunteer.gasstation.manager.biz.converter.GasMediumConverter;
import com.volunteer.gasstation.manager.biz.dto.GasMediumDTO;
import com.volunteer.gasstation.manager.biz.entity.GasMedium;
import com.volunteer.gasstation.manager.biz.service.IGasMediumService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author slzc
 * @since 2021-08-08
 */
@RestController
@RequestMapping("/mgr/biz/gasMedium")
public class GasMediumController extends BaseController {

    private IGasMediumService gasMediumService;

    public GasMediumController(IGasMediumService gasMediumService) {
        this.gasMediumService = gasMediumService;
    }

    @GetMapping
    public ResponseResult<List<GasMediumDTO>> list() {
        return new ResponseResult(GasMediumConverter.INSTANCE.mapList(gasMediumService.list()));
    }

    @PostMapping
    public ResponseResult save(@RequestBody GasMediumDTO record) {
        GasMedium GasMedium = GasMediumConverter.INSTANCE.map(record);
        GasMedium.setCreateTime(LocalDateTime.now());
        gasMediumService.save(GasMedium);
        return new ResponseResult("添加成功", ResponseResult.ACTION_TOAST);
    }

    @PutMapping(value = "{id}")
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody GasMediumDTO record) {
        GasMedium GasMedium = GasMediumConverter.INSTANCE.map(record);
        GasMedium.setId(id);
        gasMediumService.updateById(GasMedium);
        return new ResponseResult("更新成功", ResponseResult.ACTION_TOAST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        gasMediumService.removeById(id);
        return new ResponseResult("删除成功", ResponseResult.ACTION_TOAST);
    }
}

