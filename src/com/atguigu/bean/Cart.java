package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	public void clear(){
		this.map.clear();
	}
	
	public void addBook2Cart(Book book){
		CartItem cartItem = map.get(book.getId()+"");
		if(cartItem==null){
			CartItem ci = new CartItem();
			ci.setBook(book);
			ci.setCount(1);
			this.map.put(book.getId()+"", ci);
		}else{
			cartItem.setCount(cartItem.getCount()+1);
		}
	}
	
	public List<CartItem> getCartItems(){
		Collection<CartItem> values = this.map.values();
		return new ArrayList<CartItem>(values);
	}
	
	public void updateCount(String bookid,String countStr){
		CartItem cartItem = map.get(bookid);
		int count = Integer.parseInt(countStr);
		cartItem.setCount(count);
	}
	
	public void delCartItem(String bookid){
		this.map.remove(bookid);
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public int getTotalCount(){
		int count  = 0 ; 
		for (CartItem cartItem : this.getCartItems()) {
			count += cartItem.getCount();
		}
		return count;
	}
	
	public double getTotalAmount(){
		BigDecimal amount = new BigDecimal("0");
		for (CartItem cartItem : this.getCartItems()) {
			amount = amount.add(new BigDecimal(cartItem.getAmount()+""));
		}
		return amount.doubleValue();
	}
	
	public Cart() {

	}
}
