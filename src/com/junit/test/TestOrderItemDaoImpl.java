package com.junit.test;

import org.junit.Test;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;

public class TestOrderItemDaoImpl {

	private OrderItemDao oid = new OrderItemDaoImpl();
	@Test
	public void testSaveOrderItem() {
		OrderItem orderItem = new OrderItem(null, 2, 32.1, 1, "14391961579771");
		oid.saveOrderItem(orderItem);
	}

}
