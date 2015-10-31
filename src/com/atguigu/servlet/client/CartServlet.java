package com.atguigu.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.BaseServlet;
import com.atguigu.utils.WEBUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bs = new BookServiceImpl();
       
	protected void add2Cart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		Book book = bs.getBookById(bookid);
		Cart cart = WEBUtils.getCart(request);
		cart.addBook2Cart(book);
		//request.getSession().setAttribute("title", book.getTitle());
		//WEBUtils.goBack(request,response);
		int totalCount = cart.getTotalCount();
		String bookName = book.getTitle();
		Map<String,String> map = new HashMap<String, String>();
		map.put("totalCount", totalCount+"");
		map.put("bookName", "《"+bookName+"》");
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.getWriter().print(json);
		
	}
	
	protected void delCartItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		Cart cart = WEBUtils.getCart(request);
		cart.delCartItem(bookid);
		WEBUtils.goBack(request,response);
	}
	
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = WEBUtils.getCart(request);
		cart.clear();
		WEBUtils.goBack(request,response);
	}
	
	protected void updateCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String count = request.getParameter("count");
		String bookid = request.getParameter("bookid");
		Cart cart = WEBUtils.getCart(request);
		cart.updateCount(bookid, count);
		double totalAmount = cart.getTotalAmount();
		int totalCount = cart.getTotalCount();
		double amount = cart.getMap().get(bookid).getAmount();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("totalAmount", totalAmount+"");
		map.put("totalCount", totalCount);
		map.put("amount", amount+"");
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.getWriter().print(json);
		//WEBUtils.goBack(request,response);
	}
}
