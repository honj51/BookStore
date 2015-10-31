package com.atguigu.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.atguigu.utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Connection connection = null;
		try {
				connection = JDBCUtils.getConnection();
				connection.setAutoCommit(false);
				chain.doFilter(request, response);
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				request.setAttribute("error", "呜呜~~系统出错了,您稍等一下,工程师正在拼命修复...");
				request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			}finally{
				JDBCUtils.releaseConnection();
			}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
