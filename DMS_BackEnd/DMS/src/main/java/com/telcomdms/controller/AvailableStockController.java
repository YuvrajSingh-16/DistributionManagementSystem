package com.telcomdms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.AvailableStock;
import com.telcomdms.service.AvailableStockService;

@RestController
@CrossOrigin("*")
@RequestMapping("/available-stock")
public class AvailableStockController {
    @Autowired
    private AvailableStockService availableStockService;

    //add category
    @PostMapping("/")
    public ResponseEntity<AvailableStock> addStock(@RequestBody AvailableStock availableStock){
        return ResponseEntity.ok(this.availableStockService.addStock(availableStock));
    }

    //update category
    //add more stock
    @PutMapping("/update-stock")
    public ResponseEntity<?> updateStock(@RequestBody AvailableStock availableStock) throws ResourceNotFoundException {
        AvailableStock availableStock1=this.availableStockService.updateStock(availableStock);
        if(availableStock1==null)
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Updated Stock quantity must be greater than the available quantity");
        else return ResponseEntity.ok(availableStock1);
    }

    //delete stock
    @DeleteMapping("/{stockId}")
    public boolean deleteStock(@PathVariable("stockId") Long stockId){
        this.availableStockService.deleteStock(stockId);
        return true;
    }

    //get stock with given stockId
    @GetMapping("/get-stock/{stockId}")
    public AvailableStock getStock(@PathVariable("stockId") Long stockId) throws ResourceNotFoundException{
        return this.availableStockService.getStock(stockId);
    }

    //get all available stocks of user
    @GetMapping("/{userId}")
    public List<AvailableStock> getStocksOfUser(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
        return this.availableStockService.getAllStockOfUser(userId);
    }

}

