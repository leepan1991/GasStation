package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.pojo.po.Org;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface OrgMapper extends AbstractMapper {

    Org getById(@Param("id") int id);
}
