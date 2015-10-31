package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;

public interface OrderService {
	String createOrder(Cart cart,User user);
	List<Order> getOrderListByUserId(String userId);
	List<Order> getOrderList();
	int updateOrderState(String orderId,String state);
}
