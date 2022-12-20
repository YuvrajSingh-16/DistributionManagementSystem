package com.telcomdms.repository;

import com.telcomdms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmailId(String username);
    public User deleteByEmailId(String emailId);
}
