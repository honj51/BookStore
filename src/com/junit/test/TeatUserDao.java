package com.junit.test;

import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

public class TeatUserDao {
	UserDao ud = new UserDaoImpl();
	@Test
	public void testSaveUser() {
		User u = new User(null,"刘备", "123", "beibei@163.com");
		int i = ud.saveUser(u);
		System.out.println(i);
	}
	@Test
	public void testGetUserByUsernameAndPassword(){
		User u = new User();
		u.setUsername("刘备");
		u.setPassword("123");
		u = ud.getUserByUsernameAndPassword(u);
		System.out.println(u);
	}
}
