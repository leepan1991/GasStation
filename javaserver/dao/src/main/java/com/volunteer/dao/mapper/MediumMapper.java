package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Medium;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface MediumMapper extends AbstractMapper {

    int insert(Medium entity);

    int update(Medium entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Medium> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Medium entity);

    int count(@Param("entity") Medium entity);

    List<Medium> listByOrgId(@Param("orgId") int orgId);
}
