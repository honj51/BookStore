package com.atguigu.dao.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		String sql = "insert into bs_order_item(count,amount,book_id,order_id) values(?,?,?,?)";
		return this.update(sql, orderItem.getCount(),orderItem.getAmount(),orderItem.getBookId(),orderItem.getOrderId());
	}

	@Override
	public void batchSaveOrderItem(Object[][] params) {
		String sql = "insert into bs_order_item(count,amount,book_id,order_id) values(?,?,?,?)";
		this.batchUpdate(sql, params);
	}

}
