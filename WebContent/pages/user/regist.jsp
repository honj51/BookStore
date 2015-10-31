<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
.login_form {
	height: 420px;
	margin-top: 25px;
}
</style>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#code_id").click(function(){
			this.src = "code.jpg?1="+Math.random();
		});
		$("#sub_btn").click(function() {
			var username = $.trim($("[name='username']").val());
			var password = $.trim($("[name='password']").val());
			var repwd = $.trim($("[name='repwd']").val());
			var email = $.trim($("[name='email']").val());
			var code = $.trim($("[name='code']").val());

			$("[name='username']").val(username);
			$("[name='password']").val(password);
			$("[name='repwd']").val(repwd);
			$("[name='email']").val(email);
			$("[name='code']").val(code);

			var errorMsg = $(".errorMsg");
			var usernameReg = /^[A-Za-z0-9_-]{3,16}$/;
			if (!usernameReg.test(username)) {
				errorMsg.html("请输入3-16位，包含字母、数字、_、-的用户名！");
				return false;
			}
			var passwordReg = /^[A-Za-z0-9_-]{6,18}$/;
			if (!passwordReg.test(password)) {
				errorMsg.html("请输入6-18位，包含 字母、数字、_、-的密码！");
				return false;
			}
			if (!password == repwd) {
				errorMsg.html("两次输入的密码不一致");
				return false;
			}
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!emailReg.test(email)) {
				errorMsg.html("邮箱格式不正确");
				return false;
			}
			if (code == "") {
				errorMsg.html("请输入验证码");
				return false;
			}
		});
		$("[name=username]").change(function(){
			var url = "client/UserServlet";
			var username=this.value;
			var param = {method:"getUserByUserName",username:username};
			$.post(url, param, function(data){
				var errorMsg = $(".errorMsg");
				var btn = $("#sub_btn");
				if(data==1){
					errorMsg.html("用户名已经存在");
					btn.attr("disabled",true);
				}else{
					errorMsg.html("");
					btn.attr("disabled",false);
				}
			}, "text");
		});
	});
</script>
</head>
<body>
	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
	</div>

	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎注册</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册尚硅谷会员</h1>
						<span class="errorMsg">${requestScope.message}</span>
					</div>
					<div class="form">
						<form action="client/UserServlet?method=regist" method="post">
							<label>用户名称：</label> <input value="${param.username }"
								class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
								tabindex="1" name="username" /> <br /> <br /> <label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" /> <br /> <br />
							<label>确认密码：</label> <input class="itxt" type="password"
								placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
							<br /> <br /> <label>电子邮件：</label> <input
								value="${param.email }" class="itxt" type="text"
								placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
								name="email" /> <br /> <br /> <label>验证码：</label> <input
								class="itxt" type="text" style="width: 150px;" name="code" /> <img id="code_id"
								alt="" src="code.jpg"
								style="float: right; margin-right: 40px; width: 80px; height: 41px">
							<br /> <br /> <input type="submit" value="注册" id="sub_btn" />

						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>