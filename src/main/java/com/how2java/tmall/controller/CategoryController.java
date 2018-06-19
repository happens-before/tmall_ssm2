package com.how2java.tmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.ResponseUtil;
import com.how2java.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="admin_category_list",method = RequestMethod.POST)
    @ResponseBody
    public List<Category> list(Page page){
    PageHelper.offsetPage(page.getStart(),page.getCount());
    List<Category> cs= categoryService.list();
    int total = (int) new PageInfo<>(cs).getTotal();
    page.setTotal(total);
    return cs;
}
    @RequestMapping(value = "admin_category_add",method = RequestMethod.POST)
    @ResponseBody
    public Category add(Category c, HttpSession session, @RequestParam("uploadedImageFile") MultipartFile uploadedImageFile) throws IOException {
        categoryService.add(c);
        File imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return c;
    }
    @RequestMapping(value = "admin_category_delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id") int id, HttpSession session, HttpServletResponse response) throws Exception{
        categoryService.delete(id);
        File imageFolder=new File(session.getServletContext().getRealPath("img/cateage"));
        File file=new File(imageFolder,id+".jpg");
        file.delete();
        JSONObject result=new JSONObject();
        result.put("success","ok");
        ResponseUtil.write(response,result);
        return null;
    }
    @RequestMapping(value = "admin_category_findById",method = RequestMethod.POST)
    @ResponseBody
    public Category FindById(@RequestParam("id") int id) throws IOException {
        Category c= categoryService.get(id);
        return c;
    }
    @RequestMapping(value = "admin_category_update",method = RequestMethod.POST)
    @ResponseBody
    public Category update(Category c, HttpSession session, @RequestParam("uploadedImageFile") MultipartFile uploadedImageFile) throws IOException {
        categoryService.update(c);
        if(null!=uploadedImageFile &&!uploadedImageFile.isEmpty()){
            File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,c.getId()+".jpg");
            uploadedImageFile.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return c;
    }
}