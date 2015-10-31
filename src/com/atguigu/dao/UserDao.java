package com.atguigu.dao;

import com.atguigu.bean.User;

public interface UserDao {
	public User getUserByUsernameAndPassword(User user);
	public int saveUser(User user);
	public User getUserByUserName(String userName);
}
