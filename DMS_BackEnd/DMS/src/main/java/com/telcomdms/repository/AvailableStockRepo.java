package com.telcomdms.repository;

import com.telcomdms.model.AvailableStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableStockRepo extends JpaRepository<AvailableStock,Long> {
}
