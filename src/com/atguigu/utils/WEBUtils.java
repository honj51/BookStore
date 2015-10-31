package com.atguigu.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bean.Cart;

public class WEBUtils {
	public static <T> T param2Bean(HttpServletRequest request, T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				String value = request.getParameter(field.getName());
				if (value != null && !"".equals(value)) {
					BeanUtils.copyProperty(t, field.getName(), value);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}
	public static String getPagePath(HttpServletRequest request){
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryString = request.getQueryString();
		String uri = contextPath+servletPath+"?"+queryString;
		if(uri.contains("&pageNumber=")){
			uri = uri.substring(0, uri.indexOf("&pageNumber="));
		}
		return uri;
	}
	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			session.setAttribute("cart", new Cart());
		}
		return (Cart) session.getAttribute("cart");
	}
	public static void goBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = request.getHeader("referer");
		response.sendRedirect(path);
	}
}
