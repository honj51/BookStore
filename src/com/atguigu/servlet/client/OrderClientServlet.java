package com.atguigu.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.servlet.BaseServlet;
import com.atguigu.utils.WEBUtils;

public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService os = new OrderServiceImpl();

	protected void checkout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		String orderId = os.createOrder(cart, user);
		request.setAttribute("orderId", orderId);
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(
				request, response);

	}

	protected void orderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> list = os.getOrderListByUserId(user.getId() + "");
		request.setAttribute("orders", list);
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request,
				response);

	}

	protected void takeBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String state = "2";
		os.updateOrderState(orderId, state);
		WEBUtils.goBack(request, response);

	}
}
