package com.example.encrypt.controller;

import com.example.encrypt.entity.Products;
import com.example.encrypt.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody Products products){
        return productService.addProduct(products);
    }

    @GetMapping("/get/{id}")
    public Products getProductById(@PathVariable int id){
        return productService.retriveProductById(id);
    }
}
