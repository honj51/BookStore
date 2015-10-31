<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<div>
			<%@include file="/WEB-INF/include/user_info.jsp"%>
		</div>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty orders}">
				目前没有订单，呜呜呜~~~
			</c:when>
			<c:otherwise>
				<table>
					<c:forEach items="${orders}" var="order">
						<tr style="background-color: #bef">
							<td>编号</td>
							<td>日期</td>
							<td>数量</td>
							<td>金额</td>
							<td>状态</td>
						</tr>
						<tr style="background-color: #bef">
							<td>${order.id }</td>
							<td><fmt:formatDate value="${order.orderTime }"
									pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							<td>${order.totalCount }</td>
							<td>${order.totalAmount }</td>
							<c:choose>
								<c:when test="${order.state==0 }">
									<td>未发货</td>
								</c:when>
								<c:when test="${order.state==1 }">
									<td><a
										href="client/OrderClientServlet?method=takeBook&orderId=${order.id }">确认收货</a></td>
								</c:when>
								<c:when test="${order.state==2 }">
									<td>交易完成</td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td colspan="5">
								<table style="margin: 0 auto auto;">
									<tr>
										<td>图片</td>
										<td>书名</td>
										<td>单价</td>
										<td>数量</td>
										<td>金额</td>
									</tr>
									<c:forEach items="${order.orderItem }" var="order">
										<tr>
											<td><img class="book_img"
												src="${pageContext.request.contextPath}${order.book.imgPath }"></td>
											<td>${order.book.title }</td>
											<td>${order.book.price }</td>
											<td>${order.count }</td>
											<td>${order.amount }</td>
										</tr>
									</c:forEach>
								</table>
							</td>
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