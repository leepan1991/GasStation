package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.BottleLocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface BottleLocationMapper extends AbstractMapper {

    int insert(BottleLocation entity);

    List<BottleLocation> findByOrderIdAndBottleCode(@Param("orderId") String orderId, @Param("bottleCode") String bottleCode);
}
