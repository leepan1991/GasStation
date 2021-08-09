package com.volunteer.gasstation.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author by huoyao on 2021/8/8.
 */
@Data
public class PageRequest<T> {

    private Integer page = 0;
    private Integer pageSize = 10;

    public Page<T> convertMyBatisPlusPage() {
        return new Page<T>(page, pageSize);
    }
}
