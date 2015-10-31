package com.atguigu.dao;

import com.atguigu.bean.OrderItem;

public interface OrderItemDao {
	int saveOrderItem(OrderItem orderItem);
	void batchSaveOrderItem(Object[][] params);
}
