package com.volunteer.dao.mapper;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Org;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface OrgMapper extends AbstractMapper {

    int insert(Org entity);

    int update(Org entity);

    int deleteByIds(@Param("ids") Object ids);

    List<Org> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") Org entity);

    int count(@Param("entity") Org entity);

    Org getById(@Param("id") int id);

    List<Org> listAll();
}
