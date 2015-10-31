package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUserByUsernameAndPassword(User user) {
		String sql = "select id,username,password,email from bs_user where username=? and password=?";
		return this.getBean(sql, user.getUsername(),user.getPassword());
	}

	@Override
	public int saveUser(User user) {
		String sql = "insert into bs_user(username,password,email) values(?,?,?)";
		return this.update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

	@Override
	public User getUserByUserName(String userName) {
		String sql = "select id,username,password,email from bs_user where username=?";
		return this.getBean(sql, userName);
	}

}
