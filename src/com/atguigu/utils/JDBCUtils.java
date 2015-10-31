package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JDBCUtils {
	private static DataSource ds;
	static{
		ds = new ComboPooledDataSource("webDataSource");
	}
	//加入事务控制
	//private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static Map<Thread,Connection> map = new Hashtable<Thread,Connection>();
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		//return ds.getConnection();
		/*Connection connection = threadLocal.get();
		if(connection==null){
			threadLocal.set(ds.getConnection());
			connection = threadLocal.get();
		}
		return connection;*/
		Connection connection = map.get(Thread.currentThread());
		if(connection==null){
			map.put(Thread.currentThread(), ds.getConnection());
			connection = map.get(Thread.currentThread());
		}
		return connection;
	}
	/**
	 * 释放数据库连接
	 * @param c
	 */
	public static void releaseConnection(Connection c){
		if(c!=null){
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//释放链接操作在filter中操作，释放链接不仅要把链接放回连接池，还要从ThreadLocal中移除。
	public static void releaseConnection(){
		/*Connection connection = threadLocal.get();
		releaseConnection(connection);
		threadLocal.remove();*/
		Connection connection = map.get(Thread.currentThread());
		releaseConnection(connection);
		map.remove(Thread.currentThread());
	}
}
