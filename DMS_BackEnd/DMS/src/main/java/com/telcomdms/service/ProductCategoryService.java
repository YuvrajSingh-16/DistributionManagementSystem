package com.telcomdms.service;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.ProductCategories;
import com.telcomdms.model.Products;

import java.util.List;
import java.util.Set;

public interface ProductCategoryService {

    public ProductCategories addCategory(ProductCategories category);

    public ProductCategories updateCategory(ProductCategories category);

    public boolean deleteCategory(Long categoryId) throws ResourceNotFoundException;

    public List<ProductCategories> getCategories();

    public ProductCategories getCategory(Long categoryId);

    public List<ProductCategories> getCategoriesOfProduct(Products products);
}
