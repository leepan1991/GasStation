package com.volunteer.service;

import com.volunteer.dao.mapper.OrgMapper;
import com.volunteer.model.TableData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Service
public class OrgService extends AbstractService {

    @Autowired
    private OrgMapper orgMapper;

    /**
     * 创建实体
     *
     * @param entity
     */
    public int insert(Org entity) {
        return orgMapper.insert(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    public int update(Org entity) {
        return orgMapper.update(entity);
    }

    /**
     * 批量删除实体
     *
     * @param ids
     * @return
     */
    public void deleteByIds(Object ids) {
        orgMapper.deleteByIds(ids);
    }

    /**
     * 分页
     *
     * @return
     */
    public TableData listPaged(TableParameter parameter, Org entity) {
        TableData tableData = new TableData();
        tableData.data = this.orgMapper.listPaged(parameter, entity);
        parameter.setTotal(this.orgMapper.count(entity));
        tableData.page = parameter;
        return tableData;
    }

    public List<Org> listAll() {
        return orgMapper.listAll();
    }
}
