package com.telcomdms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private String orderStatus;
	@Column(nullable = false)
	private Long totalAmount;
	@Column(nullable = false)
	private Long quantity;
	@Column(nullable = false)
	private Long userId;
	@Column(nullable = false)
	private Long categoryId;

	@OneToMany(mappedBy = "orders",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderProductCategories> orderProductCategories = new ArrayList<>();
	//@OneToOne
	//private ProductCategories productCategories;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;

	public Orders() {
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderProductCategories> getOrderProductCategories() {
		return orderProductCategories;
	}

	public void setOrderProductCategories(List<OrderProductCategories> orderProductCategories) {
		this.orderProductCategories = orderProductCategories;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", date=" + date + ", orderStatus=" + orderStatus + ", totalAmount="
				+ totalAmount + ", quantity=" + quantity + ", userId=" + userId + ", categoryId=" + categoryId
				+ ", orderProductCategories=" + orderProductCategories + ", user=" + user + "]";
	}
	
}
