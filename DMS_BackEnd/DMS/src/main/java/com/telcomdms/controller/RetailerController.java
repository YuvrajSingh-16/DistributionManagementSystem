package com.telcomdms.controller;

import com.telcomdms.model.Orders;
import com.telcomdms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcomdms.model.User;
import com.telcomdms.service.UserService;

@RestController
@RequestMapping("/retailer")
@CrossOrigin()    // because of error shown while running both spring and angular app on localhost but different ports
public class RetailerController {
    @Autowired
    private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderService orderService;

    // Creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
		user.setRole("ROLE_RETAILER");
        return this.userService.createUser(user);
    }

    // Finding user
    @GetMapping("/{emailId}")
    public User getUser(@PathVariable("emailId") String emailId) throws Exception {
        return this.userService.getUser(emailId);
    }


    // Update API
    @PutMapping("/")
    public User update(@RequestBody User user) throws Exception {
		user.setRole("ROLE_RETAILER");
        return this.userService.updateUser(user);
    }

    //place order
    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody Orders order)throws Exception{
    	System.out.println(order);
        Orders orders=this.orderService.placeOrder(order);
        
        if(orders!=null){
            return ResponseEntity.ok(orders);
        }else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("Required stock not available with distributor");
        }
    }

    //get distributor of retailers
    @GetMapping("/get-distributor-of-retailer/{userId}")
    public User getDistributorOfRetailer(@PathVariable("userId") Long userId) throws Exception{
        return this.userService.getDistributorOfRetailer(userId);
    }

    
}