<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:choose>
	<c:when test="${empty sessionScope.user}">
		<a href="pages/user/login.jsp">登录</a> | 
		<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
		<a href="pages/cart/cart.jsp">购物车</a>
		<a href="pages/manager/manager.jsp">后台管理</a>
		<a href="index.jsp">首页</a>
	</c:when>
	<c:otherwise>
		<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
		<a href="client/OrderClientServlet?method=orderList">我的订单</a>
		<a href="pages/cart/cart.jsp">购物车</a>
		<a href="client/UserServlet?method=logout">注销</a>&nbsp;&nbsp;
					<a href="index.jsp">返回</a>
	</c:otherwise>
</c:choose>
