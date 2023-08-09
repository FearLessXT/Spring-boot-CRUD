package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Collection;

public interface ProductService{
    void createProduct(Product product);
    void updateProduct(String id, Product product);
    void deleteProduct(String id);
    Collection<Product> getProduct();
    Product findById(String id);
}
