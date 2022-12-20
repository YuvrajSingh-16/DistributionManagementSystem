package com.telcomdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.telcomdms.model.OrderProductCategories;


public interface OrderProductCategoriesRepo extends JpaRepository<OrderProductCategories, Long>{

}
