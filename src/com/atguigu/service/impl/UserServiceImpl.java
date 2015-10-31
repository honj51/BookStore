package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao ud = new UserDaoImpl();
	
	@Override
	public User Login(User user) {
		return ud.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		return ud.saveUser(user)==1;
	}

	@Override
	public boolean getUserByUserName(String userName) {
		User user = ud.getUserByUserName(userName);
		return !(user==null);
	}

}
