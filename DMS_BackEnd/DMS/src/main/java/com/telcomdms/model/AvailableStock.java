package com.telcomdms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AvailableStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    
    private Long availableQuantity= 0L;

    @OneToOne
    private ProductCategories productCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    public AvailableStock() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProductCategories getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(ProductCategories productCategories) {
        this.productCategories = productCategories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
