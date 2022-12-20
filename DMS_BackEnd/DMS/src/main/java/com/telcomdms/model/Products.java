package com.telcomdms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;

    @OneToMany(mappedBy = "products",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductCategories> productCategories;

    public Products() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ProductCategories> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategories> productCategories) {
        this.productCategories = productCategories;
    }
}
