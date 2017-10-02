package com.volunteer.manager.controller;

import com.volunteer.model.ManageLoginUser;
import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Customer;
import com.volunteer.pojo.po.Employee;
import com.volunteer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("employee")
public class EmployeeController extends AbstractController {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Employee entity, HttpSession session) {
        ManageLoginUser loginUser = this.getLoginUser(session);
        entity.setOrgId(loginUser.getUser().getOrgId());
        this.employeeService.insert(entity);
        return ResponseData.success("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Employee entity) {
        this.employeeService.update(entity);
        return ResponseData.success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.employeeService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, Employee entity) {
        return ResponseData.success("OK", this.employeeService.listPaged(parameter, entity));
    }
}
