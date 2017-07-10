<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MakeAccount</title>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
</head>
<body>

	<h1 style="margin-left: 120px;"><font color="lightred">单本支付</font></h1>
	<div style="margin-left: 300px;">
		<p>您当前购买的书籍是：<font color="red"><br><br>
		《${requestScope.book.b_bookname}》</font>
			，共<font color="red">${requestScope.number}</font>本，
			总计￥<font color="red">${requestScope.balance}</font>元
		</p>
		<br>
		<p>请选择支付方式</p>
		<c:if test='${requestScope.balance<=sessionScope.me.u_balance}'>
			<input type="radio" name="payway" value="account" id="bal">账户余额
		</c:if>
			<input type="radio" name="payway" value="alipay" id="alp">支付宝
		<br><br>
		
		<%-- <input type="hidden" name="bname" id="bname" value="${requestScope.book.b_bookname}"> --%>
		<input type="hidden" name="bid" id="bid" value="${requestScope.book.b_id}">
		<input type="hidden" name="bnum" id="bnum" value="${requestScope.number}">
		<input type="hidden" name="balance" id="balance" value="${requestScope.balance}">
		
		<a href="#" id="pay" style="margin-left: 180px; text-decoration: none;"><font color="red" size="5">去结账</font></a>
	</div>
	<br><br>
	
		<%-- <div>
		<c:if test="${requestScope.adrlist!=null}">
			<c:forEach items="${requestScope.adrlist}" var="adr">
				<input type="radio" name="adress" value="${adr.adress}">${adr.adress}
			</c:forEach>
		</c:if>
	</div>	 --%>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#pay").click(function(){
			var flag=$("[name=payway]:checked").val()
			if(flag==null){
				alert("请选择支付方式！")
			}else if(flag=="account"){
				var bid=$("#bid").val()
				var bnum=$("#bnum").val()
				var balance=$("#balance").val()
				window.location.href='/ebook/MakeOrder.ord?bid='+bid+'&bnum='+bnum+'&balance='+balance;
			}else if(flag=="alipay"){
				window.location.reload();
			}
		})
		
	})
</script>
</html>