package com.atguigu.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.Book;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public int saveOrder(Order order) {
		String sql = "@insert into bs_order(id,order_time,total_count,total_amount,state,user_id) values (?,?,?,?,?,?)";
		return this.update(sql, order.getId(),order.getOrderTime(),order.getTotalCount(),order.getTotalAmount(),order.getState(),order.getUserId());
	}

	@Override
	public List<Order> getOrderList() {
		String sql = "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state state from bs_order order by order_time desc";
		return this.getBeanList(sql);
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		String sql = "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state state from bs_order where user_id=? order by order_time desc";
		List<Order> orders = this.getBeanList(sql, userId);
		for (Order order : orders) {
			order.setOrderItem(this.getOrderItemList(order.getId()));
		}
		return orders;
	}

	@Override
	public int updateOrderState(String orderId, String state) {
		String sql = "update bs_order set state=? where id=?";
		return this.update(sql, state,orderId);
	}

	@Override
	public List<OrderItem> getOrderItemList(String orderId) {
		String sql = "SELECT o.count,o.amount,b.title,b.author,b.price,b.img_path FROM bs_book b JOIN bs_order_item o ON b.id=o.book_id WHERE o.order_id=?";
		List<Map<String, Object>> maps = this.getMapList(sql, orderId);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (Map<String, Object> map : maps) {
			OrderItem oi = new OrderItem();
			oi.setCount((int) map.get("count"));
			oi.setAmount((double)map.get("amount"));
			oi.setBook(new Book(null, (String)map.get("title"), (String)map.get("author"), (double)map.get("price"), 0, 0, (String)map.get("img_path")));
			orderItems.add(oi);
		}
		return orderItems;
	}
 
}
