package com.atguigu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JDBCUtils;

public class BaseDao<T> {
	private QueryRunner rq = new QueryRunner();
	private Class<T> type;
	@SuppressWarnings("unchecked")
	public BaseDao(){
		ParameterizedType pt =  (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pt.getActualTypeArguments();
		type = (Class<T>) types[0];
	}
	/**
	 * 查询一条记录
	 * @param sql
	 * @param args
	 * @return
	 */
	public T getBean(String sql,Object ... args){
		T t = null;
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			t = rq.query(c, sql, new BeanHandler<T>(type), args);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(c);
		}*/
		return t;
	}
	/**
	 * 查询多条记录，返回一个list集合
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getBeanList(String sql,Object ... args){
		List<T> list = null;
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			list = rq.query(c, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(c);
		}*/
		return list;
	}
	/**
	 * 通用的增删改方法
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(String sql,Object ... args){
		int rows = 0;
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			rows = rq.update(c, sql, args);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(c);
		}*/
		return rows;
	}
	/**
	 * 查询一个单一的值
	 * @param sql
	 * @param args
	 * @return
	 */
	public Object getSingleValue(String sql,Object ... args){
		Object o = null;
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			o = rq.query(c, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(c);
		}*/
		return o;
	}
	/**
	 * 批量插入
	 * @throws SQLException 
	 */
	public void batchUpdate(String sql,Object[][] params){
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			rq.batch(connection, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(connection);
		}*/
	}
	public List<Map<String,Object>> getMapList(String sql,Object ... params){
		List<Map<String,Object>> maps = null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			maps = rq.query(connection, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}/*finally{
			JDBCUtils.releaseConnection(connection);
		}*/
		return maps;
	}
}
