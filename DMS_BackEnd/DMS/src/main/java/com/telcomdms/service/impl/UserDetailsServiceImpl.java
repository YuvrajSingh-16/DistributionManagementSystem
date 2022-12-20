package com.telcomdms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telcomdms.model.User;
import com.telcomdms.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmailId(username);
		if(user == null) {
			logger.info("[-] User not found !!");
			throw new UsernameNotFoundException("[-] No user found!!!");
		}
		return user;
	}
}
