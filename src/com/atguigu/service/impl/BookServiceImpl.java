package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bd = new BookDaoImpl();

	@Override
	public Book getBookById(String id) {
		return bd.getBookById(id);
	}

	@Override
	public int delBook(String id) {
		return bd.delBook(id);
	}

	@Override
	public int saveBook(Book book) {
		return bd.saveBook(book);
	}

	@Override
	public int updateBook(Book book) {
		return bd.updateBook(book);
	}

	@Override
	public Page<Book> findBook(String pageNumber, int pageSize) {
		int pn = 1;
		try {
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {}
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		return bd.findBook(page);
	}

	@Override
	public Page<Book> findBookByPrice(String pageNumber, int pageSize,
			String min, String max) {
		int pn = 1;
		double minPrice = 0; 
		double maxPrice = Double.MAX_VALUE;
		try {
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {}
		
		try {
			minPrice = Double.parseDouble(min);
			maxPrice = Double.parseDouble(max);
		} catch (NumberFormatException e) {}
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		return bd.findBookByPrice(page, minPrice, maxPrice);
	}

}
