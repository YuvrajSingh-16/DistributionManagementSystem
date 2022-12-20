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
import com.telcomdms.service.impl.EmailServiceImpl;
import com.telcomdms.service.impl.ProductCategoryServiceImpl;
import com.telcomdms.service.impl.UserServiceImpl;

@SpringBootTest(classes = {ProductCategoryServiceImplTest.class})
class ProductCategoryServiceImplTest {
	
	@InjectMocks
	ProductCategoryServiceImpl productCategoryServiceImpl;
	
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
	void contextLoads() {
	}
	
	
	@Test
	void test_addCategory() {
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
		user2.setRole("ROLE_RETAILER");
		user2.setPincode("455001");
		
		User user3 = new User();
		user3.setUserId(16L);
		user3.setFirstName("Test3");
		user3.setLastName("User");
		user3.setEmailId("test3@gmail.com");
		user3.setContactDetails("24365324345");
		user3.setAddress("test address3, test city");
		user3.setPassword("testPassword3");
		user3.setRole("ROLE_DISTRIBUTOR");
		user3.setPincode("455001");
		
		List<User> lst = new ArrayList<>();
		lst.add(user1);
		lst.add(user2);
		lst.add(user3);
		
		
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
		
		List<ProductCategories> lst_prodCat = new ArrayList<>();
		lst_prodCat.add(prodCat);
		product.setProductCategories(lst_prodCat);
		
		// Available stock list
		List<AvailableStock> avalStocks1 = new ArrayList<>();
		List<AvailableStock> avalStocks2 = new ArrayList<>();
		List<AvailableStock> avalStocks3 = new ArrayList<>();
				
		AvailableStock avalStock1 = new AvailableStock();
		avalStock1.setAvailableQuantity(0L);
		avalStock1.setUser(user1);
		avalStock1.setProductCategories(prodCat);
		avalStocks1.add(avalStock1);
		
		user1.setAvailableStock(avalStocks1);
		
		// Distributor
		AvailableStock avalStock2 = new AvailableStock();
		avalStock2.setAvailableQuantity(100L);
		avalStock2.setUser(user2);
		avalStock2.setProductCategories(prodCat);
		avalStocks2.add(avalStock2);
		
		user2.setAvailableStock(avalStocks2);
		
		// User3
		AvailableStock avalStock3 = new AvailableStock();
		avalStock3.setAvailableQuantity(0L);
		avalStock3.setUser(user3);
		avalStock3.setProductCategories(prodCat);
		avalStocks3.add(avalStock3);
		
		user3.setAvailableStock(avalStocks3);
		
		when(userRepo.findAll()).thenReturn(lst);
		when(productCategoryRepo.save(prodCat)).thenReturn(prodCat);
		assertEquals(prodCat, productCategoryServiceImpl.addCategory(prodCat));
	}
	
	@Test
	void test_updateCategory() {
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
		
		Long updatedPrice = 100L;
		ProductCategories prodCatUpdated = new ProductCategories();
		prodCatUpdated.setCategoryId(67L);
		prodCatUpdated.setProducts(product);
		prodCatUpdated.setCategoryName("JIO");
		prodCatUpdated.setPrice(updatedPrice);
		
		
		when(productCategoryRepo.save(prodCat)).thenReturn(prodCatUpdated);
		assertEquals(updatedPrice, productCategoryServiceImpl.updateCategory(prodCat).getPrice());
	}
	
	@Test
	void test_deleteCategory() throws ResourceNotFoundException {
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
		
		
		assertTrue(productCategoryServiceImpl.deleteCategory(prodCat.getCategoryId()));
	}
	
	@Test
	void test_getCategories() {
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
		prodCat2.setPrice(50L);
		
		
		List<ProductCategories> prodCatLst = new ArrayList<>();
		prodCatLst.add(prodCat1);
		prodCatLst.add(prodCat2);
		
		when(productCategoryRepo.findAll()).thenReturn(prodCatLst);
		assertEquals(prodCatLst, productCategoryServiceImpl.getCategories());
	}
	
	@Test
	void test_getCategory() {
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
		
		when(productCategoryRepo.findById(prodCat.getCategoryId())).thenReturn(Optional.of(prodCat));
		assertEquals(prodCat, productCategoryServiceImpl.getCategory(prodCat.getCategoryId()));
	}
	
	@Test
	void test_getCategoriesOfProduct() {
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
		prodCat2.setPrice(50L);
		
		List<ProductCategories> prodCatLst = new ArrayList<>();
		prodCatLst.add(prodCat1);
		prodCatLst.add(prodCat2);
		
		when(productCategoryRepo.findByProducts(product)).thenReturn(prodCatLst);
		assertEquals(prodCatLst, productCategoryServiceImpl.getCategoriesOfProduct(product));
	}
}
