package com.telcomdms.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class OrderProductCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JsonBackReference
    private Orders orders;

    @ManyToOne
    private ProductCategories productCategories;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public ProductCategories getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(ProductCategories productCategories) {
		this.productCategories = productCategories;
	}
}
