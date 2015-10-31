package com.atguigu.bean;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int count;
	private double amount;
	private int bookId;
	private String orderId;
	
	private Book book;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", count=" + count + ", amount="
				+ amount + ", bookId=" + bookId + ", orderId=" + orderId + "]";
	}
	public OrderItem(Integer id, int count, double amount, int bookId,
			String orderId) {
		super();
		this.id = id;
		this.count = count;
		this.amount = amount;
		this.bookId = bookId;
		this.orderId = orderId;
	}
}
