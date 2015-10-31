package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;

public interface OrderDao {
	int saveOrder(Order order);
	List<Order> getOrderList();
	List<Order> getOrderListByUserId(String userId);
	int updateOrderState(String orderId,String state);
	List<OrderItem> getOrderItemList(String orderId);
}
