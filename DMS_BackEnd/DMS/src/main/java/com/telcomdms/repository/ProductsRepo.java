package com.telcomdms.repository;

import com.telcomdms.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,Long> {
}
