package com.telcomdms.TestControllerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.telcomdms.controller.AvailableStockController;
import com.telcomdms.exception.ResourceNotFoundException;
import com.telcomdms.model.AvailableStock;
import com.telcomdms.model.ProductCategories;
import com.telcomdms.model.Products;
import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.OrderRepo;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.repository.ProductsRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.impl.AvailableStockServiceImpl;
import com.telcomdms.service.impl.EmailServiceImpl;
import com.telcomdms.service.impl.UserServiceImpl;


@SpringBootTest(classes = {AvailableStockControllerTest.class})
class AvailableStockControllerTest {
	
	@InjectMocks
	AvailableStockController availableStockController;
	
	@Mock
	AvailableStockServiceImpl availableStockServiceImpl;
	
	@Mock
	UserServiceImpl userServiceImpl;
	
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
	
	@Test
	void test_addStock() {
		User user1 = new User();
		user1.setUserId(48L);
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("yuvrajsinghk1602@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("455001");
		// Product
		Products product = new Products();
		product.setProductId(66L);
		product.setProductName("SimCard");
	
		// Product Category
		ProductCategories prodCat = new ProductCategories();
		prodCat.setCategoryId(67L);
		prodCat.setProducts(product);
		prodCat.setCategoryName("JIO");
		prodCat.setPrice(50L);
		
		AvailableStock avalStock = new AvailableStock();
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		when(availableStockServiceImpl.addStock(avalStock)).thenReturn(avalStock);
		assertEquals(ResponseEntity.ok(avalStock), availableStockController.addStock(avalStock));
	}
	
	@Test
	void test_updateStock_pass() throws ResourceNotFoundException {
		User user1 = new User();
		user1.setUserId(48L);
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("yuvrajsinghk1602@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("455001");
		// Product
		Products product = new Products();
		product.setProductId(66L);
		product.setProductName("SimCard");
	
		// Product Category
		ProductCategories prodCat = new ProductCategories();
		prodCat.setCategoryId(67L);
		prodCat.setProducts(product);
		prodCat.setCategoryName("JIO");
		prodCat.setPrice(50L);
		
		AvailableStock avalStock = new AvailableStock();
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		AvailableStock avalStock1 = new AvailableStock();
		avalStock1.setAvailableQuantity(1000L);
		avalStock1.setUser(user1);
		avalStock1.setProductCategories(prodCat);
		
		when(availableStockServiceImpl.updateStock(avalStock1)).thenReturn(avalStock1);
		assertEquals(ResponseEntity.ok(avalStock1), availableStockController.updateStock(avalStock1));

	}
	
	@Test
	void test_updateStock_fail() throws ResourceNotFoundException {
		User user1 = new User();
		user1.setUserId(48L);
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("yuvrajsinghk1602@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("455001");
		// Product
		Products product = new Products();
		product.setProductId(66L);
		product.setProductName("SimCard");
	
		// Product Category
		ProductCategories prodCat = new ProductCategories();
		prodCat.setCategoryId(67L);
		prodCat.setProducts(product);
		prodCat.setCategoryName("JIO");
		prodCat.setPrice(50L);
		
		AvailableStock avalStock = new AvailableStock();
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		AvailableStock avalStock1 = new AvailableStock();
		avalStock1.setAvailableQuantity(1000L);
		avalStock1.setUser(user1);
		avalStock1.setProductCategories(prodCat);
		
		assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Updated Stock quantity must be greater than the available quantity"), availableStockController.updateStock(avalStock1));
	}
	
	@Test 
	void test_deleteStock() throws ResourceNotFoundException {
		User user1 = new User();
		user1.setUserId(48L);
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("yuvrajsinghk1602@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("455001");
		// Product
		Products product = new Products();
		product.setProductId(66L);
		product.setProductName("SimCard");
	
		// Product Category
		ProductCategories prodCat = new ProductCategories();
		prodCat.setCategoryId(67L);
		prodCat.setProducts(product);
		prodCat.setCategoryName("JIO");
		prodCat.setPrice(50L);
		
		AvailableStock avalStock = new AvailableStock();
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		assertTrue(availableStockController.deleteStock(avalStock.getId()));
	}
	
}
