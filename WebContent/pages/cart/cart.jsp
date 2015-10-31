<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".delA").click(function(){
			var title = $(this).parents("tr").find("td:eq(0)").html();
			if(!confirm("你确认要删除《"+title+"》吗？")){
				return false;
			}
		});
		$("#clear").click(function(){
			if(!confirm("你确认要清空购物车吗！！！")){
				return false;
			}
		});
		$(".count_input").change(function(){
			var val = $(this).val();
			var reg = /^[0-9]+$/;
			if(!reg.test(val)||val<1){
				alert("请输入正确的数字!");
				this.value = this.defaultValue;
				return ;
			}
			var url = "client/CartServlet";
			var id = this.id;
			var param={method:"updateCount",count:val,bookid:id};
			$.post(url, param, function(data){
				$(".b_count").html(data.totalCount);
				$(".b_price").html(data.totalAmount);
				$("td[class="+id+"]").html(data.amount);
			}, "json");
		});
	});
</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">购物车</span>
		<div>
			<%@include file="/WEB-INF/include/user_info.jsp"%>
		</div>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty sessionScope.cart.cartItems }">
				<h1 align="center">您的购物车中没有商品</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
						<tr>
							<td>${cartItem.book.title }</td>
							<td><input style="width: 35px;text-align: center;" class="count_input" id="${cartItem.book.id}"  type="text" value="${cartItem.count }"/></td>
							<td>${cartItem.book.price }</td>
							<td class="${cartItem.book.id}">${cartItem.amount }</td>
							<td><a class="delA" href="client/CartServlet?method=delCartItem&bookid=${cartItem.book.id }">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品
					</span> <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount}</span>元
					<span class="cart_span"><a id="clear" href="client/CartServlet?method=clear">清空购物车</a></span>
					</span> <span class="cart_span"><a href="client/OrderClientServlet?method=checkout">去结账</a></span>
				</div>
			</c:otherwise>
		</c:choose>




	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>