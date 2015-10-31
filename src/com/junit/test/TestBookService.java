package com.junit.test;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class TestBookService {
	BookService bs = new BookServiceImpl();

	@Test
	public void testGetBookById() {
		Book book = bs.getBookById("3");
		System.out.println(book);
	}

	@Test
	public void testDelBook() {
		int i = bs.delBook("15");
		System.out.println(i);
	}

	@Test
	public void testSaveBook() {
		Book book = new Book(null, "白鹿原", "陈忠实", 34, 190, 200, null);
		int i = bs.saveBook(book);
		System.out.println(i);
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book(35, "白鹿原", "陈忠实", 34, 200, 200, null);
		int i = bs.updateBook(book);
		System.out.println(i);
	}

}
