package com.junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bean.Order;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;

public class TestOrderDaoImpl {
	private OrderDao od = new OrderDaoImpl();
	@Test
	public void testSaveOrder() {
		long time = System.currentTimeMillis();
		String id = time+""+1;
		Order order = new Order(id, new Date(), 12, 23.1, 0, 1);
		od.saveOrder(order);
	}

	@Test
	public void testGetOrderList() {
		List<Order> list = od.getOrderList();
		for (Order order : list) {
			System.out.println(order);
		}
	}

	@Test
	public void testGetOrderListByUserId() {
		List<Order> list = od.getOrderListByUserId(1+"");
		for (Order order : list) {
			System.out.println(order);
		}
	}

	@Test
	public void testUpdateOrderState() {
		od.updateOrderState("14391961579771", 1+"");
	}

}
