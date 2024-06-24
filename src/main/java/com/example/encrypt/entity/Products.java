package com.example.encrypt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = com.example.encrypt.config.AesEncryption.class)
    private String name;

    @Convert(converter = com.example.encrypt.config.AesEncryption.class)
    private double price;

    public Products(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Products() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
