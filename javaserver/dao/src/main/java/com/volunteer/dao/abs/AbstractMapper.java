package com.volunteer.dao.abs;

import com.volunteer.model.TableParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface AbstractMapper {

    public <T> T selectById(@Param("id") Object id);

    int insert(Object entity);

    int update(Object entity);

    int deleteByIds(@Param("ids") Object ids);

    <T> List<T> listPaged(@Param("tableParam") TableParameter parameter, @Param("entity") T entity);
    <T> int count(@Param("entity") T entity);

    <T> List<T> listAll();
}
