package com.how2java.tmall.service;

import com.how2java.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single="type_single";
    String type_detail="type_detail";
    List<ProductImage> list(int pid,String type);
    void add(ProductImage productImage);
    void delete(int id);
    ProductImage get(int id);
    void update(ProductImage productImage);

}
