package com.telcomdms.controller;

import com.telcomdms.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.ProductCategories;
import com.telcomdms.service.ProductCategoryService;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<ProductCategories> addCategory(@RequestBody ProductCategories productCategories){
        return ResponseEntity.ok(this.productCategoryService.addCategory(productCategories));
    }

    //update category
    @PutMapping("/")
    public ResponseEntity<ProductCategories> updateCategory(@RequestBody ProductCategories productCategories) {
        return ResponseEntity.ok(this.productCategoryService.updateCategory(productCategories));
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) throws ResourceNotFoundException{
        this.productCategoryService.deleteCategory(categoryId);
    }

    //get all categories
    @GetMapping("/")
    public List<ProductCategories> getCategories() {
        return this.productCategoryService.getCategories();
    }

    //get category
    @GetMapping("/{categoryId}")
    public ProductCategories getCategory(@PathVariable("categoryId") Long categoryId){
        return this.productCategoryService.getCategory(categoryId);
    }
    //get list of categories of a product
    @GetMapping("/product/{productId}")
    public List<ProductCategories> getCategoriesOfProduct(@PathVariable("productId") Long productId){
        Products product=new Products();
        product.setProductId(productId);
        return this.productCategoryService.getCategoriesOfProduct(product);
    }
}
