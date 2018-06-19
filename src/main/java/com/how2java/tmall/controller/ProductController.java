package com.how2java.tmall.controller;

import com.how2java.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: zhaowanyue
 * @date: 2018/6/20
 * @description:
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
}
