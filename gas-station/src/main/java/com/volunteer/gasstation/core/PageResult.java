package com.volunteer.gasstation.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
public class PageResult<T> {

    private int current;
    private int pageSize;
    private long total;
    private List<T> records;

    public final static <T> PageResult<T> convert(IPage page, List<T> records) {
        PageResult<T> result = new PageResult();
        result.setCurrent((int) page.getCurrent());
        result.setPageSize((int) page.getSize());
        result.setRecords(records);
        result.setTotal(page.getTotal());
        return result;
    }
}
