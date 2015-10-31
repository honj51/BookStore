package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

public interface BookService {
	public Book getBookById(String id);
	public int delBook(String id);
	public int saveBook(Book book);
	public int updateBook(Book book);
	public Page<Book> findBook(String pageNumber,int pageSize);
	public Page<Book> findBookByPrice(String pageNumber,int pageSize,String min,String max);
}
