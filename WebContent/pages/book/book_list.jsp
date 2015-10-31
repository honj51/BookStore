<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		$(".buy").click(function() {
			var url = "client/CartServlet";
			var param = {
				method : "add2Cart",
				bookid : this.id
			};
			$.post(url, param, function(data) {
				$("#bookCount").html(data.totalCount);
				$("#bookName").html("您刚刚将<span style='color: red'>"+data.bookName+"</span>加入到了购物车中");
			}, "json");
		});
	});
</script>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">网上书城</span>
		<div>
			<%@include file="/WEB-INF/include/user_info.jsp"%>
		</div>
	</div>

	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet" method="get">
					<input type="hidden" value="findBookPageByPrice" name="method" />
					价格：<input type="text" name="min" value="${param.min }"> 元 -
					<input type="text" name="max" value="${param.max }"> 元 <input
						type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有<span id="bookCount">${sessionScope.cart.totalCount }</span>件商品</span>
				<div>
					<span id="bookName"></span>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty page.data }">
					没有找到图书
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt=""
									src="${pageContext.request.contextPath}${book.imgPath}" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span> <span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span> <span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span> <span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span> <span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span> <span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<c:choose>
										<c:when test="${book.stock<=0}">
											<span style="color: #bb0000">无货</span>
										</c:when>
										<c:otherwise>
											<button class="buy" id="${book.id }" style="color: blue">购买</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>

		<%@include file="/WEB-INF/include/page.jsp"%>

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>