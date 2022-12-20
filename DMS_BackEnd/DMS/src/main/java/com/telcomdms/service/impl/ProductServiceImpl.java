package com.telcomdms.service.impl;

import com.telcomdms.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomdms.repository.ProductsRepo;
import com.telcomdms.service.ProductService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;

    @Override
    public Products addProduct(Products products) {
        return this.productsRepo.save(products);
    }

    @Override
    public Products updateProduct(Products products) {
        return this.productsRepo.save(products);
    }

    @Override
    public Set<Products> getProducts() {
        return new LinkedHashSet<>(this.productsRepo.findAll());
    }

    @Override
    public Products getProduct(Long productId) {
        return this.productsRepo.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {this.productsRepo.deleteById(productId);
    }


}
