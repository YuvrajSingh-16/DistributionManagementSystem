package com.telcomdms.TestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.OrderRepo;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.repository.ProductsRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.impl.EmailServiceImpl;
import com.telcomdms.service.impl.UserDetailsServiceImpl;
import com.telcomdms.service.impl.UserServiceImpl;

@SpringBootTest(classes = {UserDetailsServiceImplTest.class})
class UserDetailsServiceImplTest {

	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepo userRepo;
	
	@Mock
	BCryptPasswordEncoder bcryptPasswordEncoder;	

	@Test
	void contextLoads() {
	}
	
	@Test
	void test_loadUserByUsername() {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test@gmail.com");
		user.setContactDetails("1234567890");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setRole("ROLE_RETAILER");
		user.setPincode("999999");
		
		when(userRepo.findByEmailId(user.getEmailId())).thenReturn(user);
		assertEquals(user, userDetailsServiceImpl.loadUserByUsername(user.getEmailId()));
	}
}
