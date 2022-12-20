package com.telcomdms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.Orders;
import com.telcomdms.model.User;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.UserService;

@RestController
@RequestMapping("/distributor")
@CrossOrigin()    // Allows cross origin requests
public class DistributorController {

	   	@Autowired
	    private UserService userService;

	    @Autowired
	    private UserRepo userRepo;

	    // Finding user
	    @GetMapping("/{emailId}")
	    public User getUser(@PathVariable("emailId") String emailId) throws Exception {
	        return this.userService.getUser(emailId);
	    }

	    // Update API
	    @PutMapping("/")
	    public User update(@RequestBody User user) throws Exception {
			user.setRole("ROLE_DISTRIBUTOR");
			user.setEnable(true);
	        return this.userService.updateUser(user);
	    }
	    
	 // Find user by Id
	    @GetMapping("/find-user/{userId}")
	    public User getUserById(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
	    	Optional<User> opUser = this.userRepo.findById(userId);
	    	if(opUser.isEmpty())
	    		throw new ResourceNotFoundException("Error in getUserById, user not found!!");
	    	return opUser.get();
	    }
	    
	    @GetMapping("/{Id}/get-retailers")
	    public List<User> getRetailers(@PathVariable("Id") Long Id) throws Exception{
	    	return this.userService.getRetailers(Id);
	    }
	    
	    // Get order history
	    @GetMapping("/order-history/{pincode}")
	    public List<Orders> getOrderHistory(@PathVariable("pincode") String pincode) throws Exception{
	    	return this.userService.getOrdersOfDistributor(pincode);
	    }

		//show pending order requests from retailers
		@GetMapping("/order-requests/{pincode}")
		public List<Orders> showOrderRequests(@PathVariable("pincode") String pincode) throws Exception{
			return this.userService.showOrderRequests(pincode);
		}

		// deliver order to retailer
		@GetMapping("/deliver-order/{orderId}/{categoryId}")
		public void deliverOrder(@PathVariable("orderId")Long orderId,@PathVariable("categoryId") Long categoryId) throws Exception{
			this.userService.deliverOrder(orderId,categoryId);
		}
}
