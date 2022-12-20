package com.telcomdms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.AvailableStock;
import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.AvailableStockService;


@Service
public class AvailableStockServiceImpl implements AvailableStockService{

    @Autowired
    private AvailableStockRepo availableStockRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public AvailableStock addStock(AvailableStock stock) {
        return this.availableStockRepo.save(stock);
    }

    @Override
    public AvailableStock updateStock(AvailableStock stock) throws ResourceNotFoundException {
    	Optional<AvailableStock> ops = this.availableStockRepo.findById(stock.getId());
		
		if(ops.isEmpty()) {
			throw new ResourceNotFoundException("[-] Error in updateStock(), update stock Id not found!!");
		}
		AvailableStock availableStock = ops.get();
        if(stock.getAvailableQuantity()>availableStock.getAvailableQuantity()){
        	return this.availableStockRepo.save(stock);}
    	else return null;
    }

    @Override
    public AvailableStock getStock(Long stockId) throws ResourceNotFoundException {
    	Optional<AvailableStock> ops = this.availableStockRepo.findById(stockId);
    	if(ops.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getStock(), find stock Id not found!!");
        return ops.get();
    }

    @Override
    public boolean deleteStock(Long stockId) {
        this.availableStockRepo.deleteById(stockId);
        return true;
    }

    @Override
    public List<AvailableStock> getAllStockOfUser(Long userId) throws ResourceNotFoundException {
    	Optional<User> ops = this.userRepo.findById(userId);
    	if(ops.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getAllStockOfUser(), user not found!!");
    	
        return ops.get().getAvailableStock();
    }

}

