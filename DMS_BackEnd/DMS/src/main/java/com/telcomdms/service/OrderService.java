package com.telcomdms.service;

import java.util.List;
import java.util.Set;

import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.Orders;
import com.telcomdms.model.User;

public interface OrderService {

    public Orders placeOrder(Orders order) throws Exception;

    public Orders updateOrder(Orders order);

    public Orders getOrderDetails(Long orderId) throws ResourceNotFoundException;

    public List<Orders> getOrdersOfUser(Long userId) throws ResourceNotFoundException;

    public Set<Orders> getAllOrders();

    public User getUserDetails(Long orderId) throws ResourceNotFoundException;
}
