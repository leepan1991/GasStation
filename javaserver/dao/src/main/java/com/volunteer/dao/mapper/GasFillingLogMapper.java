package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.GasFillingLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface GasFillingLogMapper extends AbstractMapper {
    int insert(GasFillingLog entity);

    int update(GasFillingLog entity);

    int deleteByIds(@Param("ids") Object ids);

    List<GasFillingLog> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") GasFillingLog entity);

    int count(@Param("entity") GasFillingLog entity);
}
