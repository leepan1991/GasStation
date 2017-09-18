package com.volunteer.service;

import com.volunteer.dao.abs.AbstractMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public abstract class AbstractService<T> {

    protected Class<T> entity;

    public AbstractService(Class<T> entity) {
        this.entity = entity;
    }

    public abstract AbstractMapper getAbstractMapper();

    public T selectById(Object id) {
        return getAbstractMapper().selectById(id);
    }
    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(T entity) {
        return getAbstractMapper().insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(T entity) {
        return getAbstractMapper().update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        getAbstractMapper().deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, T entity) {
        TableData tableData = new TableData();
        tableData.data = this.getAbstractMapper().listPaged(parameter, entity);
        parameter.setTotal(this.getAbstractMapper().count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<T> listPagedWhenExport(TableParameter parameter, T entity) {
        return this.getAbstractMapper().listPaged(parameter, entity);
    }

    public List<T> listAll() {
        return getAbstractMapper().listAll();
    }
}
