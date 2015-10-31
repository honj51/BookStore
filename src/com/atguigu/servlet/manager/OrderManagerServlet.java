package com.atguigu.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Order;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.servlet.BaseServlet;
import com.atguigu.utils.WEBUtils;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService os = new OrderServiceImpl();

	protected void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Order> list = os.getOrderList();
			request.setAttribute("orders", list);
			request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
	
	protected void sendBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			String orderId = request.getParameter("orderId");
			String state = "1";
			os.updateOrderState(orderId, state);
			WEBUtils.goBack(request, response);
	}
}
