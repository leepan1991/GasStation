package com.volunteer.manager.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Org;
import com.volunteer.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("org")
public class OrgController extends AbstractController {

    @Autowired
    private OrgService orgService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Org entity) {
        this.orgService.insert(entity);
        return ResponseData.success("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Org entity) {
        this.orgService.update(entity);
        return ResponseData.success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.orgService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, Org entity) {
        return ResponseData.success("OK", this.orgService.listPaged(parameter, entity));
    }

    @ResponseBody
    @RequestMapping(value = "listAll")
    public ResponseData listAll() {
        return ResponseData.success("OK", this.orgService.listAll());
    }
}
