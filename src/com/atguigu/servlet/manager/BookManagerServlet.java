package com.atguigu.servlet.manager;

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
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	
	private BookService bs = new BookServiceImpl();

	private static final long serialVersionUID = 1L;
       
	protected void delBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int flag = bs.delBook(id);
		if(flag==1){
			String path = request.getHeader("referer");
			response.sendRedirect(path);
		}
	}
	
	protected void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Book book = WEBUtils.param2Bean(request, new Book());
		Integer id = book.getId();
		int flag;
		String referer = request.getParameter("referer");
		if(id==null){
			flag = bs.saveBook(book);
		}else{
			flag = bs.updateBook(book);
		}
		if(flag==1){
			response.sendRedirect(referer);
		}
	}
	protected void toUpdatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Book book = bs.getBookById(id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	protected void findBookPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String uri = WEBUtils.getPagePath(request);
		//数值5是页面的大小，可以由用户传入，暂时由程序员指定
		Page<Book> page = bs.findBook(pageNumber, 5);
		page.setPath(uri);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
}
