package com.telcomdms.service.impl;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.AvailableStock;
import com.telcomdms.model.Products;
import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomdms.model.ProductCategories;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.service.ProductCategoryService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AvailableStockRepo availableStockRepo;

    @Override
    public ProductCategories addCategory(ProductCategories category) {
        ProductCategories productCategories=this.productCategoryRepo.save(category);
        List<User> users=this.userRepo.findAll();

        for(User user: users){
            AvailableStock availableStock=new AvailableStock();
            availableStock.setAvailableQuantity(0L);
            availableStock.setUser(user);
            availableStock.setProductCategories(category);
            this.availableStockRepo.save(availableStock);

            List<AvailableStock> availableStock1=user.getAvailableStock();
            availableStock1.add(availableStock);
            user.setAvailableStock(availableStock1);
            this.userRepo.save(user);

        }
        return productCategories;
    }

    @Override
    public ProductCategories updateCategory(ProductCategories category) {
        return this.productCategoryRepo.save(category);
    }

    @Override
    public boolean deleteCategory(Long categoryId) throws ResourceNotFoundException {
    	try {
    		this.productCategoryRepo.deleteById(categoryId);
            return true;
    	}catch(Exception e) {
    		throw new ResourceNotFoundException("[-] Error in deleteCategory(), category not found...");
    	}
    }

    @Override
    public List<ProductCategories> getCategories() {
        return new ArrayList<>(this.productCategoryRepo.findAll());
    }

    @Override
    public ProductCategories getCategory(Long categoryId) {
        return this.productCategoryRepo.findById(categoryId).get();
    }

    @Override
    public List<ProductCategories> getCategoriesOfProduct(Products products) {
        return this.productCategoryRepo.findByProducts(products);
    }
}
