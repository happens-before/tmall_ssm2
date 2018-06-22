package com.how2java.tmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zhaowanyue
 * @date: 2018/6/20
 * @description:
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping(value = "admin_product_list",method = RequestMethod.POST)
    @ResponseBody
    public List<Product> list(@RequestParam("cid") int cid, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        return ps;
    }
    //增删改
    @RequestMapping(value = "admin_product_add",method = RequestMethod.POST)
    @ResponseBody
    public Product add(Product product)
    {
        productService.add(product);
        return product;
    }
    @RequestMapping(value = "admin_product_delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id") int id, HttpServletResponse response)throws Exception
    {
        productService.delete(id);
        JSONObject result=new JSONObject();
        result.put("success","ok");
        ResponseUtil.write(response,result);
        return null;
    }
    @RequestMapping(value = "admin_product_update",method = RequestMethod.POST)
    @ResponseBody
    public Product update(Product product)
    {
        productService.update(product);
        return product;
    }
}
