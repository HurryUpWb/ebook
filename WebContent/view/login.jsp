<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basepath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="/ebook/css/loginstyle.css">
</head>
<body>
	<div class="login-wrap" style="margin-top: 100px;">
		<h2>登录</h2>

		<div class="form">
			<input type="text" placeholder="邮箱/手机号" id="un" name="un" />
			<input type="password" placeholder="密码" id="pw" name="pw" />
			<button id="login">登录</button>
			<a href="#" id="regiter">
				<p>没有账号，点击注册</p>
			</a>
		</div>
	</div>
	<script src="<%=basepath%>/js/jquery-1.6.2.min.js"></script>
	<script src="<%=basepath%>/js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#regiter").click(function(){
				window.location.href='/ebook/view/register/Register.jsp'
			})
			
			$("#login").click(function(){
				var un=$("#un").val();
				var pw=$("#pw").val();
				if(un==null || un=="" || pw==null || pw==""){
					alert("用户名或密码不能为空！")
					return false;
				}else{
					var urls='<%=basepath%>/getUsr.usr?name='+un+'&pwd='+pw;
					$.ajax({
						type:'post',
						url:urls,
					    dataType:'json', 
						success:function(data){
							if(data.msg=="success"){
								window.location.href='<%=basepath%>/view/Mine.jsp'
								parent.location.reload();
							}else if(data.msg=="no record"){
								alert("账号或密码不对！");
							}
						},
						error:function(){
							alert("system error!")
						}
						
					})
				}
			})
		})
	</script>
</body>
</html>