package com.example.encrypt.service;

import com.example.encrypt.entity.Products;
import com.example.encrypt.repository.ProductsRrepo;

public interface ProductService {



    String addProduct(Products products);

    Products retriveProductById(int id);
}
