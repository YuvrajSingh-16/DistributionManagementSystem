package com.telcomdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.Orders;
import com.telcomdms.model.User;
import com.telcomdms.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;


    //update order
    @PutMapping("/")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders order){
        return ResponseEntity.ok(this.orderService.updateOrder(order));
    }

    //get Order details
    @GetMapping("/{orderId}")
    public Orders getOrderDetails(@PathVariable("orderId") Long orderId) throws ResourceNotFoundException{
        return this.orderService.getOrderDetails(orderId);
    }
    
    //get orders of a user
    @GetMapping("/retailer/{userId}")
    public ResponseEntity<?> getOrdersOfUser(@PathVariable("userId")Long userId) throws ResourceNotFoundException{
        return ResponseEntity.ok(this.orderService.getOrdersOfUser(userId));
    }

    //get details of user who placed the order(retailer)
    @GetMapping("/user/{orderId}")
    public User getUserDetails(@PathVariable("orderId") Long orderId) throws ResourceNotFoundException{
        return this.orderService.getUserDetails(orderId);
    }
}
