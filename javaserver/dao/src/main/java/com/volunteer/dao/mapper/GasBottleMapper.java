package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Employee;
import com.volunteer.pojo.po.GasBottle;
import com.volunteer.pojo.po.OrderBottle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface GasBottleMapper extends AbstractMapper {
    int insert(GasBottle entity);

    int update(GasBottle entity);

    int deleteByIds(@Param("ids") Object ids, @Param("orgId") int orgId);

    List<GasBottle> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") GasBottle entity);

    int count(@Param("entity") GasBottle entity);

    int updateNextCheckTime(@Param("ids") int[] ids, @Param("nextCheckDate") String nextCheckDate);

    int updateLocation(GasBottle gasBottle);

    int updateWhenStorage(GasBottle gasBottle);
    int updateWhenDelivery(GasBottle gasBottle);
    int updateDealWhenStorage(OrderBottle orderBottle);
    int updateDealWhenDelivery(OrderBottle orderBottle);

    GasBottle getById(@Param("orgId") int orgId, @Param("code") String code);
}
