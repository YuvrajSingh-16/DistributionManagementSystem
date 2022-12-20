package com.telcomdms.TestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.telcomdms.model.AvailableStock;
import com.telcomdms.model.OrderProductCategories;
import com.telcomdms.model.Orders;
import com.telcomdms.model.ProductCategories;
import com.telcomdms.model.Products;
import com.telcomdms.model.User;
import com.telcomdms.repository.AvailableStockRepo;
import com.telcomdms.repository.OrderRepo;
import com.telcomdms.repository.ProductCategoryRepo;
import com.telcomdms.repository.ProductsRepo;
import com.telcomdms.repository.UserRepo;
import com.telcomdms.service.impl.EmailServiceImpl;
import com.telcomdms.service.impl.UserServiceImpl;



@SpringBootTest(classes = {UserServiceImplTest.class})
class UserServiceImplTest {
	
	@InjectMocks
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
	@Order(1)
	void test_createUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setRole("ROLE_RETAILER");
		user.setPincode("999999");
		
		List<ProductCategories> lst = new ArrayList<>();
		ProductCategories productCategory = new ProductCategories();
		productCategory.setCategoryId(10L);
		productCategory.setCategoryName("JIO");
		lst.add(productCategory);
		
		// Data stubbing
		when(userServiceImpl.createUser(user)).thenReturn(user);
		when(productCategoryRepo.findAll()).thenReturn(lst);
		
		assertEquals(user,userServiceImpl.createUser(user));
	}

	@Test
	@Order(2)
	void test_updateUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test1@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setRole("ROLE_ADMIN");
		user.setPincode("999999");
		
		User updatedUser = new User();
		updatedUser.setFirstName("Test");
		updatedUser.setLastName("User");
		updatedUser.setEmailId("test1@gmail.com");
		updatedUser.setContactDetails("2345478");
		updatedUser.setAddress("test street, test city");
		updatedUser.setPassword("testPasswordNew");
		updatedUser.setRole("ROLE_ADMIN");
		updatedUser.setPincode("99999999");
		
		List<ProductCategories> lst = new ArrayList<>();
		ProductCategories productCategory = new ProductCategories();
		productCategory.setCategoryId(10L);
		productCategory.setCategoryName("JIO");
		lst.add(productCategory);
		
		// Data stubbing
		when(userRepo.findByEmailId(user.getEmailId())).thenReturn(user);
		when(userServiceImpl.updateUser(updatedUser)).thenReturn(updatedUser);
		when(productCategoryRepo.findAll()).thenReturn(lst);
		
		assertEquals(updatedUser,userServiceImpl.updateUser(updatedUser));
	}
	
	@Test
	@Order(3)
	void test_getUser() throws Exception{
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test2@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setRole("ROLE_DISTRIBUTOR");
		user.setPincode("999999");
		
		when(userRepo.findByEmailId(user.getEmailId())).thenReturn(user);
		assertEquals(user, userServiceImpl.getUser("test2@gmail.com"));
	}
	
	@Test()
	@Order(4)
	void test_deleteUser() throws Exception {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmailId("test2@gmail.com");
		user.setContactDetails("2436532345");
		user.setAddress("test address, test city");
		user.setPassword("testPassword");
		user.setRole("ROLE_DISTRIBUTOR");
		user.setPincode("999999");
		
		
		when(userRepo.findByEmailId(user.getEmailId())).thenReturn(user);
		assertTrue(userServiceImpl.deleteUser("test2@gmail.com"));
	}
	
	@Test()
	@Order(5)
	void test_showProductDetails() throws Exception {
		List<Products> lst = new ArrayList<>();
		Products prod = new Products();
		prod.setProductId(10L);
		prod.setProductName("SimCard");
		lst.add(prod);
		
		when(productsRepo.findAll()).thenReturn(lst);
		assertEquals(lst, userServiceImpl.showProductDetails());
	}
	
	@Test()
	void test_getDistributors() throws Exception {
		User user1 = new User();
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("test1@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("99999933");
		
		User user2 = new User();
		user2.setFirstName("Test2");
		user2.setLastName("User");
		user2.setEmailId("test2@gmail.com");
		user2.setContactDetails("+912436532345");
		user2.setAddress("test address2, test city");
		user2.setPassword("testPassword2");
		user2.setRole("ROLE_DISTRIBUTOR");
		user2.setPincode("99999459");
		
		User user3 = new User();
		user3.setFirstName("Test3");
		user3.setLastName("User");
		user3.setEmailId("test3@gmail.com");
		user3.setContactDetails("24365324345");
		user3.setAddress("test address3, test city");
		user3.setPassword("testPassword3");
		user3.setRole("ROLE_DISTRIBUTOR");
		user3.setPincode("9999929");
		
		List<User> lst = new ArrayList<>();
		lst.add(user1);
		lst.add(user2);
		lst.add(user3);
		
		List<User> ans = new ArrayList<>();
		ans.add(user2);
		ans.add(user3);
		
		when(userRepo.findAll()).thenReturn(lst);
		assertEquals(ans, userServiceImpl.getDistributors());
	}
	
	@Test()
	void test_getRetailers() throws Exception {
		User user1 = new User();
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("test1@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("99999933");
		
		User user2 = new User();
		user2.setFirstName("Test2");
		user2.setLastName("User");
		user2.setEmailId("test2@gmail.com");
		user2.setContactDetails("+912436532345");
		user2.setAddress("test address2, test city");
		user2.setPassword("testPassword2");
		user2.setRole("ROLE_RETAILER");
		user2.setPincode("99999459");
		
		User user3 = new User();
		user3.setFirstName("Test3");
		user3.setLastName("User");
		user3.setEmailId("test3@gmail.com");
		user3.setContactDetails("24365324345");
		user3.setAddress("test address3, test city");
		user3.setPassword("testPassword3");
		user3.setRole("ROLE_DISTRIBUTOR");
		user3.setPincode("9999929");
		
		List<User> lst = new ArrayList<>();
		lst.add(user1);
		lst.add(user2);
		lst.add(user3);
		
		List<User> ans = new ArrayList<>();
		ans.add(user1);
		ans.add(user2);
		
		when(userRepo.findAll()).thenReturn(lst);
		assertEquals(ans, userServiceImpl.getRetailers());
	}
	
	@Test
	void test_disableUser() throws Exception{
		User user1 = new User();
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("test1@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("99999933");
		
		when(userRepo.findByEmailId(user1.getEmailId())).thenReturn(user1);
		when(userRepo.save(user1)).thenReturn(user1);
		assertEquals(false, userServiceImpl.disableUser(user1.getEmailId()).isEnable());
	}
	
	@Test
	void test_enableUser() throws Exception{
		User user1 = new User();
		user1.setFirstName("Test1");
		user1.setLastName("User");
		user1.setEmailId("test1@gmail.com");
		user1.setContactDetails("2432432345");
		user1.setAddress("test address1, test city");
		user1.setPassword("testPassword1");
		user1.setRole("ROLE_RETAILER");
		user1.setPincode("99999933");
		user1.setEnable(false);
		
		when(userRepo.findByEmailId(user1.getEmailId())).thenReturn(user1);
		when(userRepo.save(user1)).thenReturn(user1);
		assertEquals(true, userServiceImpl.enableUser(user1.getEmailId()).isEnable());
	}
	
	@Test
	void test_getRetailersOfDistributor() throws Exception{
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
		
		List<User> ans = new ArrayList<>();
		ans.add(user1);
		ans.add(user2);
		
		Optional<User> usr3Op = Optional.of(user3);
		when(userRepo.findAll()).thenReturn(lst);
		when(userRepo.findById(user3.getUserId())).thenReturn(usr3Op);
		
		assertEquals(ans, userServiceImpl.getRetailers(16L));
	}
	
	@Test
	void test_getOrdersOfDistributor() throws Exception {
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
		
		Orders order1 = new Orders();
		order1.setQuantity(100L);
		order1.setTotalAmount(250L);
		order1.setOrderStatus("PLACED");
		order1.setUser(user1);
		
		List<Orders> ordLst1 = new ArrayList<>();
		List<Orders> ordLst2 = new ArrayList<>();
		List<Orders> ordLst3 = new ArrayList<>();
		ordLst1.add(order1);
		
		user1.setOrderlist(ordLst1);
		user2.setOrderlist(ordLst2);
		user3.setOrderlist(ordLst3);
		
		List<OrderProductCategories> ordProdLst = new ArrayList<>();
		
        ProductCategories productCat = new ProductCategories();
        productCat.setCategoryName("Jio");
        productCat.setPrice(100L);
        
        Products prods = new Products();
        prods.setProductName("SimCard");
        
        // Many to Many mapping of order and productCategories using OrderProductCategories
        OrderProductCategories orderProductCategories = new OrderProductCategories();
        orderProductCategories.setOrders(order1);
        orderProductCategories.setProductCategories(productCat);
        ordProdLst.add(orderProductCategories);

        order1.getOrderProductCategories().addAll(ordProdLst);
        order1.setUserId(order1.getUser().getUserId());
        
        
		when(userRepo.findAll()).thenReturn(lst);
		assertEquals(ordLst1, userServiceImpl.getOrdersOfDistributor("455001"));
	}
	
	@Test
//	@DisplayName("😱")
	void test_deliverOrder() throws Exception {
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
		
		User user2 = new User();
		user2.setUserId(12L);
		user2.setFirstName("Test2");
		user2.setLastName("User");
		user2.setEmailId("test2@gmail.com");
		user2.setContactDetails("+912436532345");
		user2.setAddress("test address2, test city");
		user2.setPassword("testPassword2");
		user2.setRole("ROLE_DISTRIBUTOR");
		user2.setPincode("455001");
		
		List<User> lst = new ArrayList<>();
		lst.add(user1);
		lst.add(user2);
		
		// Create available stock of retailer and distributor
			// 1. avail_stock -> 1.1 product_category >> 1.2 product
		
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
		
		// Retailer
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
		
		// Extra availableStock
		AvailableStock avalStock3 = new AvailableStock();
		AvailableStock avalStock4 = new AvailableStock();
		
		// Order
		Orders order1 = new Orders();
		order1.setOrderId(16L);
		order1.setQuantity(50L);
		order1.setTotalAmount(2500L);
		order1.setOrderStatus("UNDER_PROCESS");
		order1.setUser(user1);
		
		// Adding order to Distributor orderList
		List<Orders> ordLst1 = new ArrayList<>();
		ordLst1.add(order1);
		user1.setOrderlist(ordLst1);
		
		List<OrderProductCategories> ordProdLst = new ArrayList<>();
        
        // Many to Many mapping of order and productCategories using OrderProductCategories
        OrderProductCategories orderProductCategories = new OrderProductCategories();
        orderProductCategories.setOrders(order1);
        orderProductCategories.setProductCategories(prodCat);
        ordProdLst.add(orderProductCategories);

        order1.getOrderProductCategories().addAll(ordProdLst);
        order1.setUserId(order1.getUser().getUserId());
        /* Order object completed here */
        
        
		Optional<Orders> opOrder = Optional.of(order1);
		Optional<User> opUser = Optional.of(user1);
		
		when(orderRepo.findById(order1.getOrderId())).thenReturn(opOrder);
		when(userRepo.findById(user1.getUserId())).thenReturn(opUser);
		when(userRepo.findAll()).thenReturn(lst);
		
		
		assertTrue(userServiceImpl.deliverOrder(order1.getOrderId(), prodCat.getCategoryId()));
	}
	
	@Test
	void test_showOrderRequests() throws Exception {
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
		
		Orders order1 = new Orders();
		order1.setQuantity(100L);
		order1.setTotalAmount(250L);
		order1.setOrderStatus("UNDER_PROCESS");
		order1.setUser(user1);
		
		Orders order2 = new Orders();
		order2.setQuantity(100L);
		order2.setTotalAmount(500L);
		order2.setOrderStatus("PLACED");
		order2.setUser(user2);
		
		
		List<Orders> ordLst1 = new ArrayList<>();
		List<Orders> ordLst2 = new ArrayList<>();
		List<Orders> ordLst3 = new ArrayList<>();
		
		ordLst1.add(order1);
		ordLst2.add(order2);
		
		user1.setOrderlist(ordLst1);
		user2.setOrderlist(ordLst2);
		user3.setOrderlist(ordLst3);
		
		List<OrderProductCategories> ordProdLst = new ArrayList<>();
		
        ProductCategories productCat = new ProductCategories();
        productCat.setCategoryName("Jio");
        productCat.setPrice(100L);
        
        Products prods = new Products();
        prods.setProductName("SimCard");
        
        // Many to Many mapping of order and productCategories using OrderProductCategories
        OrderProductCategories orderProductCategories = new OrderProductCategories();
        orderProductCategories.setOrders(order1);
        orderProductCategories.setProductCategories(productCat);
        ordProdLst.add(orderProductCategories);

        order1.getOrderProductCategories().addAll(ordProdLst);
        order1.setUserId(order1.getUser().getUserId());
        
		
		when(userRepo.findAll()).thenReturn(lst);
		assertEquals(ordLst1, userServiceImpl.showOrderRequests(user3.getPincode()));
	}
	
	
	@Test
	void test_getDistributorOfRetailer() throws Exception {
		User user1 = new User();
		user1.setUserId(32L);
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
		
		Optional<User> opUser1 = Optional.of(user1);
		
		when(userRepo.findAll()).thenReturn(lst);
		when(userRepo.findById(user1.getUserId())).thenReturn(opUser1);
		assertEquals(user3, userServiceImpl.getDistributorOfRetailer(user1.getUserId()));
	}
}
