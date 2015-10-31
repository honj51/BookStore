package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private Book book;
	private int count;
	public CartItem() {
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		BigDecimal count = new BigDecimal(this.getCount()+"");
		return price.multiply(count).doubleValue();
	}
}
