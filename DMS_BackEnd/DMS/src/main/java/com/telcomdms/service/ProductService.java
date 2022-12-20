package com.telcomdms.service;

import com.telcomdms.model.Products;

import java.util.Set;

public interface ProductService {

    public Products addProduct(Products products);

    public Products updateProduct(Products products);

    public Set<Products> getProducts();

    public Products getProduct(Long productId);

    public void deleteProduct(Long productId);

}
