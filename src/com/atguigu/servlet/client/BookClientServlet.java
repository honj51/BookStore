package com.atguigu.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.BaseServlet;
import com.atguigu.utils.WEBUtils;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bs = new BookServiceImpl();

	protected void findBookPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		Page<Book> page = bs.findBook(pageNumber, 4);
		page.setPath(WEBUtils.getPagePath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
	}
	
	protected void findBookPageByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		String pageNumber = request.getParameter("pageNumber");
		Page<Book> page = bs.findBookByPrice(pageNumber, 4, min, max);
		page.setPath(WEBUtils.getPagePath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
	}
}
