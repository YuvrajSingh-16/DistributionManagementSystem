package com.telcomdms.service.impl;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomdms.repository.OrderRepo;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.OrderService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Override
    public Orders placeOrder(Orders order) throws Exception {
        int flag=0;
        Long categoryId=order.getCategoryId();
        Long userId = order.getUserId();
        
        Optional<User> ops= this.userRepo.findById(userId);
        if(ops.isEmpty())
        	throw new ResourceNotFoundException("[-] Error in PlaceOrder(), user not found");
        
        String pincode = ops.get().getPincode();
        
        // Fetching all distributors
        List<User> distributors= this.userRepo.findAll().stream().filter(user -> user.getRole().equals("ROLE_DISTRIBUTOR")).collect(Collectors.toList());
        
        for(User distributor:distributors)
        {
            if(Objects.equals(distributor.getPincode(), pincode))
            {   flag=1;
            	Optional<ProductCategories> pcOpn = this.productCategoryRepo.findById(categoryId);
            	if(pcOpn.isEmpty())
            		throw new ResourceNotFoundException("[-] Error in PlaceOrder(), productCategroy not found");
                ProductCategories productCategories= pcOpn.get();

                // Set total amount
                order.setTotalAmount((order.getQuantity())*(productCategories.getPrice()));

                // Traversing availableStocks of distributor and placing order if AvailableQuantity is more that RequiredQuantity
                Long requiredQuantity = order.getQuantity();
                List<AvailableStock> availableStock = distributor.getAvailableStock();
                for (AvailableStock as : availableStock)
                {	
                    if (as.getProductCategories().getCategoryId().equals(categoryId)) { // If required product exists
                    	if(as.getAvailableQuantity() >= (requiredQuantity))
	                    {
	                        List<OrderProductCategories> ordProdLst = new ArrayList<>();
	                        Optional<ProductCategories> opProductCat = this.productCategoryRepo.findById(categoryId);
	                        
	                        if(opProductCat.isEmpty())
	                        	throw new ResourceNotFoundException("[-] Error in placeOrder, productCategory not found...");
	                        
	                        ProductCategories productCat = opProductCat.get();
	                        
	                        // Many to Many mapping of order and productCategories using OrderProductCategories
	                        OrderProductCategories orderProductCategories = new OrderProductCategories();
	                        orderProductCategories.setOrders(order);
	                        orderProductCategories.setProductCategories(productCat);
	                        ordProdLst.add(orderProductCategories);
	
	                        order.getOrderProductCategories().addAll(ordProdLst);
	                        order.setUserId(order.getUser().getUserId());
	                        
	                        this.productCategoryRepo.save(productCat);
	                        return this.orderRepo.save(order);
	                    }else {
	                    	throw new Exception("[-] RequiredQuantity is more that AvailableQuantity.");
	                    }
                    }
                }break;
            }
        }if(flag==0){
            throw new Exception("[-] Distributor does not exist for this pincode");
        }
        return null;
    }

    @Override
    public Orders updateOrder(Orders order) {
        return this.orderRepo.save(order);
    }

    @Override
    public Orders getOrderDetails(Long orderId) throws ResourceNotFoundException {
    	Optional<Orders> opOrder = this.orderRepo.findById(orderId);
    	if(opOrder.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getOrderDetails, order not found...");
    	
        return opOrder.get();
    }

    @Override
    public List<Orders> getOrdersOfUser(Long userId) throws ResourceNotFoundException {
    	Optional<User> opOrder = this.userRepo.findById(userId);
    	
    	if(opOrder.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getOrdersOfUser, user not found...");
    	
        return opOrder.get().getOrderlist();
    }

    @Override
    public Set<Orders> getAllOrders() {
        return new HashSet<>(this.orderRepo.findAll());
    }

    @Override
    public User getUserDetails(Long orderId) throws ResourceNotFoundException {
    	Optional<Orders> opOrder = this.orderRepo.findById(orderId);
    	if(opOrder.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getUserDetails, Order not found...");
        Optional<User> opUser = this.userRepo.findById(opOrder.get().getUserId());
    	if(opUser.isEmpty())
    		throw new ResourceNotFoundException("[-] Error in getUserDetails, User not found...");
        return opUser.get();
    }

}
