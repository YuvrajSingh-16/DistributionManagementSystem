package com.telcomdms.service;

import java.util.List;
import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.AvailableStock;


public interface AvailableStockService {
    public AvailableStock addStock(AvailableStock stock);

    public AvailableStock updateStock(AvailableStock stock) throws ResourceNotFoundException;

    public AvailableStock getStock(Long stockId) throws ResourceNotFoundException;

    public boolean deleteStock(Long stockId);

    public List<AvailableStock> getAllStockOfUser(Long userId) throws ResourceNotFoundException;
}

