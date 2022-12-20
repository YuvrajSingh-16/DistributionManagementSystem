package com.telcomdms.controller;

import com.telcomdms.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.telcomdms.service.ProductService;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //add product
    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Products products) {
        Products product1 = this.productService.addProduct(products);
        return ResponseEntity.ok(product1);
    }

    //get product
    @GetMapping("/{productId}")
    public Products getProduct(@PathVariable("productId") Long productId){
        return this.productService.getProduct(productId);
    }

    //get all products
    @GetMapping("/")
    public Set<Products> getProducts(){
        return this.productService.getProducts();
    }

    //update product
    @PutMapping("/")
    public Products updateProduct(@RequestBody Products products) {
        return this.productService.updateProduct(products);
    }

    //delete product
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        this.productService.deleteProduct(productId);
    }
}