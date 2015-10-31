package com.atguigu.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.BaseServlet;
import com.atguigu.utils.WEBUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String scode = (String)session.getAttribute("code");
		session.removeAttribute("code");
		
		if (scode != null && code.equals(scode)) {
			User user = WEBUtils.param2Bean(request, new User());
			if (us.regist(user)) {
				response.sendRedirect(request.getContextPath()
						+ "/pages/user/regist_success.jsp");
			} else {
				request.setAttribute("message", "该用户名已被占用！");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}

	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		User user = WEBUtils.param2Bean(request, new User());
		User u = us.Login(user);
		if (!(u == null)) {
			request.getSession().setAttribute("user", u);
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");
		} else {
			request.setAttribute("message", "用户名或者密码错误！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		}
	}

	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	protected void getUserByUserName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		if(us.getUserByUserName(username)){
			response.getWriter().print(1);
		}else{
			response.getWriter().print(0);
		}
	}
}
