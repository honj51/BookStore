package com.junit.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.atguigu.utils.JDBCUtils;

public class TestJDBCUtils {
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection c = JDBCUtils.getConnection();
		System.out.println(c);
	}

}
