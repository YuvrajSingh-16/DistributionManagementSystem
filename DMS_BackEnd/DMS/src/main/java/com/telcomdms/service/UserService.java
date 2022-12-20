package com.telcomdms.service;

import java.util.List;

import com.telcomdms.model.Orders;
import com.telcomdms.model.Products;
import com.telcomdms.model.User;

public interface UserService {
    // For all users
    public User createUser(User user) throws Exception;

    public User getUser(String emailId) throws Exception;

    public boolean deleteUser(String emailId) throws Exception;

    public User updateUser(User user) throws Exception;

    // For Admin
    public List<User> getDistributors() throws Exception;

    public User disableUser(String emailId);

    public User enableUser(String emailId);

	List<User> getRetailers() throws Exception;
	
    public List<Products> showProductDetails()throws Exception;

    // For Distributor
    List<User> getRetailers(Long Id) throws Exception;

    List<Orders> getOrdersOfDistributor(String pincode) throws Exception;

    public User getDistributorOfRetailer(Long userId) throws Exception;

    public boolean deliverOrder(Long orderId,Long categoryId) throws Exception;

    public List<Orders> showOrderRequests(String pincode) throws Exception;

}
