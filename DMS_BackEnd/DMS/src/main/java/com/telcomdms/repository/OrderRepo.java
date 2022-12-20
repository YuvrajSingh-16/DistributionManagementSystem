package com.telcomdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telcomdms.model.Orders;
import com.telcomdms.model.User;

import java.util.Set;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    Set<Orders> findByUser(User user);
}
