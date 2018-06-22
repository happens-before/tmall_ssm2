package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductExample;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhaowanyue
 * @date: 2018/6/20
 * @description:
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setFirstProductImage(p);
        setCategory(p);
        return p;
    }
    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }
    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }
    @Override
    public List<Product> list(int cid) {
        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        List<Product> products=productMapper.selectByExample(example);
        setCategory(products);
        setFirstProductImage(products);
        return products;
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage>  productImages=productImageService.list(product.getId(),ProductImageService.type_single);
        if(!productImages.isEmpty())
        {
            ProductImage productImage=productImages.get(0);
            product.setProductImage(productImage);
        }
    }
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
