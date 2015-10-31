package com.atguigu.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//由时间戳和用户id拼接而成
	private String id;
	private Date orderTime;
	private int totalCount;
	private double totalAmount;
	private int state;
	private int userId;
	
	private List<OrderItem> orderItem;
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Order(String id, Date orderTime, int totalCount, double totalAmount,
			int state, int userId) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.state = state;
		this.userId = userId;
	}
	public Order() {
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", totalCount="
				+ totalCount + ", totalAmount=" + totalAmount + ", state="
				+ state + ", userId=" + userId + "]";
	}
	
}
