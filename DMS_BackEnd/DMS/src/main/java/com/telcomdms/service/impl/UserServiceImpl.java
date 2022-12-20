package com.telcomdms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.telcomdms.exception.AlreadyExistsException;
import com.telcomdms.exception.NotAllowedException;
import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.*;
import com.telcomdms.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telcomdms.service.EmailService;
import com.telcomdms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String RETAILER = "ROLE_RETAILER";
	private static final String DISTRIBUTOR = "ROLE_DISTRIBUTOR";
	private static final String ADMIN = "ROLE_ADMIN";
	
	@Autowired
    private ProductsRepo productsRepo;
	
	@Autowired 
	private UserRepo userRepo;

	@Autowired
	private ProductCategoryRepo productCategoryRepo;

	@Autowired
	private AvailableStockRepo availableStockRepo;

	@Autowired
	private OrderRepo orderRepo;
	
    @Autowired
    private EmailService emailService;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @Override
    public User createUser(User user) throws Exception {
    	try {
    		// Admin user create restriction
            if(user.getRole().equals(ADMIN))
            	throw new NotAllowedException("[-] Cannot create Admin user");
        	
            User local=this.userRepo.findByEmailId(user.getEmailId());
            
            if(local!=null){
                logger.info("[-] User is already present");
                throw new AlreadyExistsException("[-] User already present");
            }else{
    			// fetch all the product categories and initialize available stock for each category = 0
    			local = this.userRepo.save(user);
    			String pass = user.getPassword();
    			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
    			
    			List<ProductCategories> productCategories=this.productCategoryRepo.findAll();
    			List<AvailableStock> availableStocks= new ArrayList<>();

    			for(ProductCategories pc: productCategories){
    				AvailableStock as= new AvailableStock();
    				as.setProductCategories(pc);
    				as.setAvailableQuantity(0L);
    				as.setUser(user);
    				availableStocks.add(as);
    				this.availableStockRepo.save(as);
    			}

    			user.setAvailableStock(availableStocks);
    			this.userRepo.save(user);
    			
    			
    			if(user.getRole().equals(DISTRIBUTOR)) {
    		    	String msg = "<p>Hello "+user.getFirstName()+" "+user.getLastName()+",</p> <p>your request for being a distributor in TelCom DMS application is been approved by the admin and here are your login credentials</p> <p>Login Email: "+user.getEmailId()+"</p> <p>Password: "+pass+"</p> <p>Thanks</p>";
    		    	this.emailService.sendSimpleEmail(user.getEmailId(), "DMS Application: Distributor credentials", msg);
    		    	logger.info("[+] Credentials Mail send to Distributor");
    			}
    		}

            return local;
    	}catch(Exception e) {
    		throw new ResourceNotFoundException("[-] Exception in createUser()...");
    	}
    }
    
    @Override
    public User updateUser(User user) throws Exception {
    	logger.info("[+] Updating user");
    
	    User local=this.userRepo.findByEmailId(user.getEmailId());
	    if(local!=null) {
		    user.setUserId(local.getUserId());
		    user.setRole(local.getRole());
		    user.setPassword(local.getPassword());
		    local = this.userRepo.save(user);
	    }else{
	    	throw new ResourceNotFoundException("[-] User does not exist!");
	    }
	    	return local;
    }
    
    @Override
    public User getUser(String emailId) throws Exception {
    	try {
    		logger.info("[+] Fetching user details");
            return this.userRepo.findByEmailId(emailId);
    	}catch(Exception e) {
    		throw new ResourceNotFoundException("[-] Inside getUser(), user with given emailId not found...");
    	}
    }

    @Override
    public boolean deleteUser(String emailId) throws Exception {
    	try {
    		logger.info("[+] Deleting User..");
            this.userRepo.deleteById(Long.valueOf(this.userRepo.findByEmailId(emailId).getUserId()));
            return true;
    	}catch(Exception e) {
    		throw new ResourceNotFoundException("[-] Error in deleteUser(), emailId not found...");
    	}
    }
	
	
	// Admin Functions
	@Override
    public List<Products> showProductDetails() throws Exception {
		try {
			logger.info("[+] Fetching product details");
			return this.productsRepo.findAll();
		} catch(Exception e) {
			throw new ResourceNotFoundException("[-] Error in showProductDetails()...");
		}
       
    }

	@Override
	public List<User> getDistributors() throws Exception {
		try {
			// Search for user with user.role == "ROLE_DISTRIBUTOR" in user table 
			logger.info("[+] Searching for Distributors in user table");
			return this.userRepo.findAll().stream().filter(user -> user.getRole().equals(DISTRIBUTOR)).collect(Collectors.toList());			
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Error in getDistributors()...");
		}
	}
	
	@Override
	public List<User> getRetailers() throws Exception {
		try {
			// Search for user with user.role == "ROLE_RETAILER" in user table 
			logger.info("[+] Searching for retailers in user table");
			return this.userRepo.findAll().stream().filter(user -> user.getRole().equals(RETAILER)).collect(Collectors.toList());			
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Error in getRetailers()...");
		}
	}
	
	@Override
	public User disableUser(String emailId) {
		User user = this.userRepo.findByEmailId(emailId);
		user.setEnable(false);
		return this.userRepo.save(user);
	}

	@Override
	public User enableUser(String emailId) {
		User user = this.userRepo.findByEmailId(emailId);
		user.setEnable(true);
		return this.userRepo.save(user);
	}
	
	// Distributor functions
	@Override
	public List<User> getRetailers(Long ID) throws Exception {
		try {
			// Find all the retailers with current distributor pincode
			logger.info("[+] Fetching retialers of distributor");
			return this.userRepo.findAll().stream().filter(user -> user.getPincode().equals(this.userRepo.findById(ID).get().getPincode()) && !Long.valueOf(user.getUserId()).equals(ID) && user.getRole().equals(RETAILER)).collect(Collectors.toList());
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Exception in getRetailers..");
		}
	}
	
	// Get orders of given distributor using pincode
	@Override
	public List<Orders> getOrdersOfDistributor(String pincode) throws Exception {
		try {
			logger.info("[+] Fetching orders of distributor");
			List<User> users = this.userRepo.findAll().stream().filter(user -> user.getPincode().equals(pincode)&&user.getRole().equals(RETAILER)).collect(Collectors.toList());
			List<Orders> orders = new ArrayList<>();
			
			for(User user: users) {
				for(Orders order: user.getOrderlist())
					orders.add(order);
			}return orders;
			
		} catch(Exception e) {
			throw new ResourceNotFoundException("[-] Exception in getOrdersOfDistributor");
		}
	}


	@Override
	public boolean deliverOrder(Long orderId,Long categoryId) throws Exception {
		try {
			logger.info("[+] Inside DeliverOrder");
			
			boolean flag = false;
			
			Optional<Orders> ops = this.orderRepo.findById(orderId);
			if(ops.isEmpty())
				throw new ResourceNotFoundException("[-] Error in deliverOrder(), order not found");
			
			Orders orders = ops.get();
			orders.setOrderStatus("DELIVERED");
			Long requiredQuantity = orders.getQuantity();
			User retailer = orders.getUser();
			
			Optional<User> opuser = this.userRepo.findById(retailer.getUserId());
			if(opuser.isEmpty())
				throw new ResourceNotFoundException("[-] Error in deliverOrder(), user not found");
			
			String pincode = opuser.get().getPincode();
			User distributor = this.userRepo.findAll().stream().filter(user -> user.getPincode().equals(pincode) && user.getRole().equalsIgnoreCase(DISTRIBUTOR)).collect(Collectors.toList()).get(0);
			
			
			// If stock of required product is available then stocks are shifted from distributor to retailer
			List<AvailableStock> availableStockDistributor = distributor.getAvailableStock();
			for (AvailableStock asd : availableStockDistributor) {
				if (asd.getProductCategories().getCategoryId().equals(categoryId)) {
					// Deducting the quantity from distributor's stocks 
					asd.setAvailableQuantity(asd.getAvailableQuantity()-requiredQuantity);
					flag=true;
					this.availableStockRepo.save(asd);
				}
			}
			if(!flag){
				return flag;
			}
			
			List<AvailableStock> availableStockRetailer = retailer.getAvailableStock();
			for (AvailableStock asr : availableStockRetailer) {
				if (asr.getProductCategories().getCategoryId().equals(categoryId)) {
					// Adding the quantity to retailer's stocks 
					asr.setAvailableQuantity(asr.getAvailableQuantity()+requiredQuantity);
					this.availableStockRepo.save(asr);
				}
			}
			
			this.orderRepo.save(orders);
			
			String msg = "<p>Hello "+retailer.getFirstName()+" "+retailer.getLastName()+",</p> <p>your order request for order Id "+orders.getOrderId()+" is been delivered by your distributor.</p> <p>Please login to check the changes</p> <p>Thanks</p>";
	    	this.emailService.sendSimpleEmail(retailer.getEmailId(), "DMS Application: Order Delivered", msg);
			
			return flag;
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Exception in deliverOrder..");
		}
	}
	
	
	// Returns list of orders waiting for approval from distributor
	@Override
	public List<Orders> showOrderRequests(String pincode) throws Exception {
		try {
			logger.info("[+] Fetching order requests");
			
			List<User> users = this.userRepo.findAll().stream().filter(user -> user.getPincode().equals(pincode)&&user.getRole().equals(RETAILER)).collect(Collectors.toList());
			List<Orders> orders = new ArrayList<>();

			for(User user: users) {
				for(Orders order: user.getOrderlist())
				{
					if(order.getOrderStatus().equals("UNDER_PROCESS"))
						orders.add(order);
				}
			}return orders;
			
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Exception in showOrderRequests");
		}
	}
	
	
	// Retailer functions
	@Override
	public User getDistributorOfRetailer(Long userId) throws Exception{
		try {
			logger.info("[+] Fetching distributor of retailer");
			return this.userRepo.findAll().stream().filter(user -> user.getPincode().equals(this.userRepo.findById(userId).get().getPincode())&& user.getRole().equalsIgnoreCase(DISTRIBUTOR)).collect(Collectors.toList()).get(0);
		}catch(Exception e) {
			throw new ResourceNotFoundException("[-] Exception in getDistributorOfRetailer(), cannot find distributor");
		}
	}
}
