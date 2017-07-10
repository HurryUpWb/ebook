<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String basepath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mine</title>
</head>
<body>
<div style="margin-left:10px; margin-top: 60px; overflow: hidden;">
	<div>
		<img alt="user" src="/ebook/images/mainuser.png" style="width:120px;height: 120px;">
	</div>
	<div style="margin-top: -115px; margin-left: 145px;">
		<c:if test="${sessionScope.me!=null}">
			<table>
				<tr>
					<td>账号：</td>
					<td>${sessionScope.me.u_account}</td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td>${sessionScope.me.u_name}</td>
				</tr>
				<tr>
					<td>余额：</td>
					<td>${sessionScope.me.u_balance}</td>
				</tr>
				<tr>
					<td>会员等级：</td>
					<td>
						<c:if test="${sessionScope.me.u_authority==1}">普通会员</c:if>
						<c:if test="${sessionScope.me.u_authority==2}">金牌会员</c:if>
					</td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td>${sessionScope.me.u_email}</td>
				</tr>
				<tr>
					<td>手机：</td>
					<td>${sessionScope.me.u_telephone}</td>
				</tr>
			</table>
		</c:if>
	</div>
	<div style="margin-top:150px; margin-left:80px;">
		<!-- <img alt="cart" id="cart" src="/ebook/images/cart.png" style="width:60px;height: 60px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<img alt="order" id="order" src="/ebook/images/order.png" style="width:60px;height: 60px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img alt="record" id="record" src="/ebook/images/record.png" style="width:50px;height: 50px;">
			
	</div>
	<div style="margin-left:60px;">
		<!-- <font>我的购物车</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<font>未完成订单</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<font>购买历史</font>
	</div>
	<iframe src="" id="ifr"  style="margin-left:405px; margin-top:-450px;height:500px; width:710px;">
	
	</iframe>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#order").click(function(){
			$("#ifr").attr("src","/ebook/getOrder.ord?flag=fail")
		})
		
		$("#record").click(function(){
			$("#ifr").attr("src","/ebook/getOrder.ord")
		})
	})
</script>
</html>