package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.GasBottle;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface GasBottleMapper extends AbstractMapper {
    int updateNextCheckTime(@Param("ids") int[] ids, @Param("nextCheckDate") String nextCheckDate);

    int updateLocation(GasBottle gasBottle);
}
