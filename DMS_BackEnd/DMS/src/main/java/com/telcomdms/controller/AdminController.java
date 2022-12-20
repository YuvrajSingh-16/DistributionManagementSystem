package com.telcomdms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.telcomdms.model.ProductCategories;
import com.telcomdms.model.Products;
import com.telcomdms.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.Orders;
import com.telcomdms.model.User;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.EmailService;
import com.telcomdms.service.OrderService;
import com.telcomdms.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserRepo userRepo;

    // Creating Distributor
    @PostMapping("/create-distributor")
    public User createUser(@RequestBody User user) throws Exception {
        user.setRole("ROLE_DISTRIBUTOR");
        user.setEnable(true);
        return this.userService.createUser(user);
    }
    
    // Create Admin
    @PostMapping("/")
    public User create(@RequestBody User user) throws Exception {
    	user.setRole("ROLE_ADMIN");
    	user.setEnable(true);
        return this.userService.createUser(user);
    }

    @PutMapping("/")
    public User update(@RequestBody User user) throws Exception {
    	user.setRole("ROLE_ADMIN");
    	user.setEnable(true);
        return this.userService.updateUser(user);
    }

    // Finding user
    @GetMapping("/{emailId}")
    public User getUser(@PathVariable("emailId") String emailId) throws Exception {
        return this.userService.getUser(emailId);
    }

    // Find user by Id
    @GetMapping("/find-user/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
    	Optional<User> opUser = this.userRepo.findById(userId);
    	if(opUser.isEmpty())
    		throw new ResourceNotFoundException("Error in getUserById, user not found!!");
    	return opUser.get();
    }

    //details of all the products
    @GetMapping("/products")
    public List<ProductCategories> ProductDetails() throws Exception{
       return this.productCategoryService.getCategories();
    }
    
    
    @GetMapping("/get-distributors")
    public List<User> getDistributors() throws Exception{
    	return this.userService.getDistributors();
    }
    
    @GetMapping("/get-retailers")
    public List<User> getRetailers() throws Exception{
    	return this.userService.getRetailers();
    }
    
    //get all orders
    @GetMapping("/get-all-orders")
    public Set<Orders> getAllOrders(){
        return new HashSet<>(this.orderService.getAllOrders());
    }

    //get distributor of retailers
    @GetMapping("/get-distributor-of-retailer/{userId}")
    public User getDistributorOfRetailer(@PathVariable("userId") Long userId) throws Exception{
        return this.userService.getDistributorOfRetailer(userId);
    }

    @GetMapping("/disable-retailer/{emailId}")
    public ResponseEntity<?> disableRetailer(@PathVariable("emailId") String emailId) throws Exception{
    	this.userService.disableUser(emailId);
    	return ResponseEntity.ok("[+] Disabled retailer with email "+emailId);
    }
    
    @GetMapping("/enable-retailer/{emailId}")
    public ResponseEntity<?> enableRetailer(@PathVariable("emailId") String emailId) throws Exception{
    	this.userService.enableUser(emailId);
    	return ResponseEntity.ok("[+] Enabled retailer with email "+emailId);
    }
    
    @GetMapping("/disable-distributor/{emailId}")
    public ResponseEntity<?> disableDistributor(@PathVariable("emailId") String emailId) throws Exception{
    	this.userService.disableUser(emailId);
    	return ResponseEntity.ok("[+] Disabled retailer with email "+emailId);
    }
    
    @GetMapping("/enable-distributor/{emailId}")
    public ResponseEntity<?> enableDistributor(@PathVariable("emailId") String emailId) throws Exception{
    	this.userService.enableUser(emailId);
    	return ResponseEntity.ok("[+] Enabled retailer with email "+emailId);
    }
}
