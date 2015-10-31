package com.atguigu.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao od = new OrderDaoImpl();


	@Override
	public String createOrder(Cart cart, User user) {
		OrderItemDao oid = new OrderItemDaoImpl();
		BookDao bd = new BookDaoImpl();
		
		String id = System.currentTimeMillis()+""+user.getId();
		Date orderTime = new Date();
		int totalCount = cart.getTotalCount();
		double totalAmount = cart.getTotalAmount();
		int state = 0;
		int userId = user.getId();
		Order order = new Order(id, orderTime, totalCount, totalAmount, state, userId);
		od.saveOrder(order);
		List<CartItem> list = cart.getCartItems();
		
		Object[][] orderitems = new Object[list.size()][];
		Object[][] books = new Object[list.size()][];
		
		int index = 0;
		for (CartItem cartItem : list) {
			Book b = cartItem.getBook();
			
			int count = cartItem.getCount();
			double amount = cartItem.getAmount();
			int bookId = b.getId();
			
			orderitems[index] = new Object[]{count, amount, bookId, id};
			
			int sales = b.getSales()+cartItem.getCount();
			int stock = b.getStock()-cartItem.getCount();
			books[index] = new Object[]{sales, stock,bookId};
			
			index++;
		}
		oid.batchSaveOrderItem(orderitems);
		bd.batchUpdateSalesAndStock(books);
		cart.clear();
		return id;
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		return od.getOrderListByUserId(userId);
	}

	@Override
	public List<Order> getOrderList() {
		return od.getOrderList();
	}

	@Override
	public int updateOrderState(String orderId, String state) {
		return od.updateOrderState(orderId, state);
	}

}
