package com.telcomdms.TestControllerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.telcomdms.controller.RetailerController;
import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.OrderRepo;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.repository.ProductsRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.UserService;
import com.telcomdms.service.impl.EmailServiceImpl;


@SpringBootTest(classes = {RetailerControllerTest.class})
class RetailerControllerTest {

	@Mock
	UserService userService;
	
	@Mock
	UserRepo userRepo;
	
	@Mock
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Mock 
	ProductCategoryRepo productCategoryRepo;
	
	@Mock
	EmailServiceImpl emailServiceImpl;
	
	@Mock 
	AvailableStockRepo availableStockRepo;
	
	@Mock 
	ProductsRepo productsRepo;
	
	@Mock
	OrderRepo orderRepo;
	
	@InjectMocks
	RetailerController retailerController;
	
	
	@Test
	void test_createUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setPincode("999999");
		
		User user1 = new User();
		user1.setFirstName("Test");
		user1.setLastName("User");
		user1.setRole("ROLE_RETAILER");
		user1.setEmailId("test@gmail.com");
		user1.setContactDetails("2436532345");
		user1.setAddress("test address, test city");
		user1.setPassword("testPassword");
		user1.setPincode("999999");
		
		when(userService.createUser(user)).thenReturn(user1);
		assertEquals(user1, retailerController.createUser(user));
	}
	
	@Test
	void test_getUser() throws Exception {
		User user1 = new User();
		user1.setFirstName("Test");
		user1.setLastName("User");
		user1.setRole("ROLE_RETAILER");
		user1.setEmailId("test@gmail.com");
		user1.setContactDetails("2436532345");
		user1.setAddress("test address, test city");
		user1.setPassword("testPassword");
		user1.setPincode("999999");
		
		when(userService.getUser(user1.getEmailId())).thenReturn(user1);
		assertEquals(user1, retailerController.getUser(user1.getEmailId()));
	}
	
	@Test
	void test_updateUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setPincode("999999");
		
		User user1 = new User();
		user1.setFirstName("Test");
		user1.setLastName("User");
		user1.setRole("ROLE_RETAILER");
		user1.setEmailId("test@gmail.com");
		user1.setContactDetails("912234442");
		user1.setAddress("test address1, test city1");
		user1.setPassword("testPassword");
		user1.setPincode("9999999");
		
		when(userService.updateUser(user1)).thenReturn(user1);
		assertEquals(user1, retailerController.update(user1));
	}
	
	@Test
	void test_getDistributorOfRetailer() throws Exception {
		User user1 = new User();
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("test1@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("455001");
		
		User user2 = new User();
		user2.setFirstName("Test2");
		user2.setLastName("User");
		user2.setEmailId("test2@gmail.com");
		user2.setContactDetails("+912436532345");
		user2.setAddress("test address2, test city");
		user2.setPassword("testPassword2");
		user2.setRole("ROLE_DISTRIBUTOR");
		user2.setPincode("455001");
		
		when(userService.getDistributorOfRetailer(user1.getUserId())).thenReturn(user2);
		assertEquals(user2, retailerController.getDistributorOfRetailer(user1.getUserId()));
	}
}
