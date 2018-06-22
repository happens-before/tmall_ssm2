package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: zhaowanyue
 * @date: 2018/6/22
 * @description:
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "admin_user_list",method = RequestMethod.POST)
    @ResponseBody
    public List<User> list( Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> us= userService.list();
        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);
        return us;
    }
}
