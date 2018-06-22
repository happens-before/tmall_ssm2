package com.how2java.tmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.PropertyValueService;
import com.how2java.tmall.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zhaowanyue
 * @date: 2018/6/22
 * @description:
 */
@Controller
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;
    @RequestMapping(value = "admin_propertyValue_edit",method = RequestMethod.POST)
    @ResponseBody
    public List<PropertyValue> edit(int pid) {
        Product p = productService.get(pid);
        propertyValueService.init(p);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        return pvs;
    }
    @RequestMapping(value = "admin_propertyValue_update",method = RequestMethod.POST)
    @ResponseBody
    public String update(PropertyValue pv, HttpServletResponse response)throws Exception {
        propertyValueService.update(pv);
        JSONObject result=new JSONObject();
        result.put("success","ok");
        ResponseUtil.write(response,result);
        return null;
    }
}
