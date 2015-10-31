package com.junit.test;

import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

public class TestServices {
	UserService us = new UserServiceImpl();

	@Test
	public void testLogin() {
		User user = new User();
		user.setUsername("刘备");
		user.setPassword("123");
		User u = us.Login(user);
		System.out.println(u);
	}

	@Test
	public void testRegist() {
		User user = new User(null, "张飞", "456", "feifei@qq.com");
		boolean b = us.regist(user);
		System.out.println(b);
	}

}
