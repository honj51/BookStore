package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {


	@Override
	public Book getBookById(String id) {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where id=?";
		return this.getBean(sql, id);
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
		return this.update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
	}

	@Override
	public int delBook(String id) {
		String sql = "delete from bs_book where id=?";
		return this.update(sql, id);
	}

	@Override
	public int saveBook(Book book) {
		String sql = "insert into bs_book(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
		return this.update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
	}

	@Override
	public Page<Book> findBook(Page<Book> page) {
		String sql = "select count(id) from bs_book";
		long totalRecord = (long) this.getSingleValue(sql);
		page.setTotalRecord((int)totalRecord);
		sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book limit ?,?";
		List<Book> data = this.getBeanList(sql, page.getIndex(),page.getPageSize());
		page.setData(data);
		return page;
	}

	@Override
	public Page<Book> findBookByPrice(Page<Book> page, double min, double max) {
		String sql = "select count(id) from bs_book where price>=? and price<=?";
		long totalRecord = (long) this.getSingleValue(sql,min,max);
		if(totalRecord==0){
			return page;
		}
		page.setTotalRecord((int)totalRecord);
		sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where price>=? and price<=? limit ?,?";
		List<Book> data = this.getBeanList(sql, min,max,page.getIndex(),page.getPageSize());
		page.setData(data);
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		String sql = "update bs_book set sales=?,stock=? where id=?";
		this.batchUpdate(sql, params);
		
	}

}
