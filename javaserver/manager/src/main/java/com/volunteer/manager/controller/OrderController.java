package com.volunteer.manager.controller;

import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.po.Order;
import com.volunteer.pojo.po.Role;
import com.volunteer.service.OrderService;
import com.volunteer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody Order order) {
        this.orderService.insert(order);
        return ResponseData.success("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseData update(@RequestBody Order order) {
        this.orderService.update(order);
        return ResponseData.success("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResponseData delete(String id) {
        this.orderService.deleteByIds(new Object[] { id });
        return ResponseData.success("删除成功");
    }

    @ResponseBody
    @RequestMapping(value = "listPaged")
    public ResponseData listPaged(TableParameter parameter, Order entity) {
        return ResponseData.success("OK", this.orderService.listPaged(parameter, entity));
    }
}
