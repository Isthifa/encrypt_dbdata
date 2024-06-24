package com.example.encrypt.repository;


import com.example.encrypt.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRrepo extends JpaRepository<Products, Integer>{

    Products save(Products products);

    Products findById(int id);

    void deleteById(int id);

}
