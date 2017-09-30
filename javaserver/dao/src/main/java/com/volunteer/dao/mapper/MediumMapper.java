package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.Medium;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface MediumMapper extends AbstractMapper {

    List<Medium> listByOrgId(@Param("orgId") int orgId);
}
