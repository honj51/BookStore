package com.atguigu.dao;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;


public interface BookDao {
	public Book getBookById(String id);
	public int updateBook(Book book);
	public int delBook(String id);
	public int saveBook(Book book);
	public Page<Book> findBook(Page<Book> page);
	public Page<Book> findBookByPrice(Page<Book> page,double min,double max);
	public void batchUpdateSalesAndStock(Object[][] params);
}
