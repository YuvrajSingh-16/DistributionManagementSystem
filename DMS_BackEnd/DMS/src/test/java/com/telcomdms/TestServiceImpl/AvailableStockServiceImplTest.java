package com.telcomdms.TestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

@SpringBootTest(classes = {AvailableStockServiceImplTest.class})
class AvailableStockServiceImplTest {
	
	@InjectMocks
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
		
		when(availableStockRepo.save(avalStock)).thenReturn(avalStock);
		assertEquals(avalStock, availableStockServiceImpl.addStock(avalStock));
	}
	
	@Test
	void test_updateStock() throws ResourceNotFoundException {
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
		avalStock1.setAvailableQuantity(100L);
		avalStock1.setUser(user1);
		avalStock1.setProductCategories(prodCat);
		
		
		when(availableStockRepo.findById(avalStock.getId())).thenReturn(Optional.of(avalStock));
		when(availableStockRepo.save(avalStock1)).thenReturn(avalStock1);
		assertEquals(avalStock1, availableStockServiceImpl.updateStock(avalStock1));
	}
	
	@Test
	void test_getStock() throws ResourceNotFoundException {
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
		avalStock.setId(122L);
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		
		when(availableStockRepo.findById(122L)).thenReturn(Optional.of(avalStock));
		assertEquals(avalStock, availableStockServiceImpl.getStock(122L));
	}
	
	@Test
	void test_deleteStock() {
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
		avalStock.setId(122L);
		avalStock.setAvailableQuantity(0L);
		avalStock.setUser(user1);
		avalStock.setProductCategories(prodCat);
		
		assertTrue(availableStockServiceImpl.deleteStock(avalStock.getId()));
	}
	
	@Test
	void test_getAllStockOfUser() throws ResourceNotFoundException {
		User user1 = new User();
		user1.setUserId(48L);
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("yuvraj.kiraula@impetus.com");
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
		ProductCategories prodCat1 = new ProductCategories();
		prodCat1.setCategoryId(67L);
		prodCat1.setProducts(product);
		prodCat1.setCategoryName("JIO");
		prodCat1.setPrice(50L);
		
		ProductCategories prodCat2 = new ProductCategories();
		prodCat2.setCategoryId(68L);
		prodCat2.setProducts(product);
		prodCat2.setCategoryName("Airtel");
		prodCat2.setPrice(60L);
		
		ProductCategories prodCat3 = new ProductCategories();
		prodCat3.setCategoryId(69L);
		prodCat3.setProducts(product);
		prodCat3.setCategoryName("VI");
		prodCat3.setPrice(70L);
		
		AvailableStock avalStock1 = new AvailableStock();
		avalStock1.setId(122L);
		avalStock1.setAvailableQuantity(0L);
		avalStock1.setUser(user1);
		avalStock1.setProductCategories(prodCat1);
		
		AvailableStock avalStock2 = new AvailableStock();
		avalStock2.setId(123L);
		avalStock2.setAvailableQuantity(40L);
		avalStock2.setUser(user1);
		avalStock2.setProductCategories(prodCat2);
		
		AvailableStock avalStock3 = new AvailableStock();
		avalStock3.setId(124L);
		avalStock3.setAvailableQuantity(60L);
		avalStock3.setUser(user1);
		avalStock3.setProductCategories(prodCat3);
		
		List<AvailableStock> avalStocks = new ArrayList<>();
		avalStocks.add(avalStock1);
		avalStocks.add(avalStock2);
		avalStocks.add(avalStock3);
		
		user1.setAvailableStock(avalStocks);
		
		when(userRepo.findById(user1.getUserId())).thenReturn(Optional.of(user1));
		assertEquals(avalStocks, availableStockServiceImpl.getAllStockOfUser(user1.getUserId()));
	}
}
