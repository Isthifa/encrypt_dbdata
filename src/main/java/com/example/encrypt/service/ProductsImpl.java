package com.example.encrypt.service;

import com.example.encrypt.entity.Products;
import com.example.encrypt.repository.ProductsRrepo;
import org.springframework.stereotype.Service;

@Service
public class ProductsImpl implements ProductService{

    private final ProductsRrepo productsRrepo;

    public ProductsImpl(ProductsRrepo productsRrepo) {
        this.productsRrepo = productsRrepo;
    }

    @Override
    public String addProduct(Products products) {
        Products products1=productsRrepo.save(products);
        return "Product added with id: "+products1.getId();
    }

    @Override
    public Products retriveProductById(int id) {
        return productsRrepo.findById(id);
    }
}
