package com.atguigu.service;

import com.atguigu.bean.User;

public interface UserService {
	public User Login(User user);
	public boolean regist(User user);
	public boolean getUserByUserName(String userName);
}
