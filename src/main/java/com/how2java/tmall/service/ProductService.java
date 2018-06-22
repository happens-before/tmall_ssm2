package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(int cid);
    void add(Product product);
    void delete(int id);
    Product get(int id);
    void update(Product product);
}
