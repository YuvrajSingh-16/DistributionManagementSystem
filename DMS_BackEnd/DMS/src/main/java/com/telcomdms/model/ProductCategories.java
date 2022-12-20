package com.telcomdms.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;
    private Long price;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "productCategories")
    @JsonIgnore
    private AvailableStock availableStock;

    @ManyToOne(fetch = FetchType.EAGER)
    private Products products;

    @OneToMany(mappedBy = "productCategories",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderProductCategories> orderProductCategories;

    
    public ProductCategories() {
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public AvailableStock getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(AvailableStock availableStock) {
        this.availableStock = availableStock;
    }

    public List<OrderProductCategories> getOrderProductCategories() {
        return orderProductCategories;
    }

    public void setOrderProductCategories(List<OrderProductCategories> orderProductCategories) {
        this.orderProductCategories = orderProductCategories;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
