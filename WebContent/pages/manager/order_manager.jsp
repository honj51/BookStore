<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">订单管理系统</span>
		<div>
			<%@include file="/WEB-INF/include/manager_info.jsp"%>
		</div>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty orders}">
				目前没有订单，呜呜呜~~~
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>编号</td>
						<td>日期</td>
						<td>数量</td>
						<td>金额</td>
						<td>状态</td>
					</tr>
					<c:forEach items="${orders}" var="order">
						<tr>
							<td>${order.id }</td>
							<td><fmt:formatDate value="${order.orderTime }"
									pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							<td>${order.totalCount }</td>
							<td>${order.totalAmount }</td>
							<c:choose>
								<c:when test="${order.state==0 }">
									<td><a
										href="manager/OrderManagerServlet?method=sendBook&orderId=${order.id }">点击发货</a></td>
								</c:when>
								<c:when test="${order.state==1 }">
									<td>等待确认收货</td>
								</c:when>
								<c:when test="${order.state==2 }">
									<td>交易完成</td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>