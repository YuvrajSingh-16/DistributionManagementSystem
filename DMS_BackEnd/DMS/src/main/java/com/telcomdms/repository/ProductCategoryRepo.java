package com.telcomdms.repository;

import com.telcomdms.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import com.telcomdms.model.ProductCategories;

import java.util.List;

public interface ProductCategoryRepo extends JpaRepository<ProductCategories, Long> {
    public List<ProductCategories> findByProducts(Products products);
}
