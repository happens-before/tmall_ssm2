package com.how2java.tmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zhaowanyue
 * @date: 2018/6/8
 * @description:
 */
@Controller
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;
    @RequestMapping(value = "admin_property_list",method = RequestMethod.POST)
    @ResponseBody
    public List<Property> list(@RequestParam("cid") int cid,Page page) {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());
        return ps;
    }
    //增删改
    @RequestMapping(value = "admin_property_add",method = RequestMethod.POST)
    @ResponseBody
    public Property add(Property property)
    {
        propertyService.add(property);
        return property;
    }
    @RequestMapping(value = "admin_property_delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id") int id, HttpServletResponse response)throws Exception
    {
        propertyService.delete(id);
        JSONObject result=new JSONObject();
        result.put("success","ok");
        ResponseUtil.write(response,result);
        return null;
    }
    @RequestMapping(value = "admin_property_update",method = RequestMethod.POST)
    @ResponseBody
    public Property update(Property property)
    {
        propertyService.update(property);
        return property;
    }
}
