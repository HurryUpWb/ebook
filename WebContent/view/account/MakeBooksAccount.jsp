<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>duobenzhifu</title>
<style type="text/css">
	td{
		width:160px;
		height: 60px;
	}
</style>
</head>
<body>
	<div style="margin-left:80px;">
		<c:if test="${requestScope.blist!=null}">
		<p>您当前购买的书籍是：</p>
		<table>
				<tr>
					<th align="center">书名</th>
					<th align="center">作者</th>
					<th align="center">出版社</th>
					<th align="center">单价</th>
					<th align="center">数量</th>
				</tr>
			<c:forEach items="${requestScope.blist}" var="b">
				<tr>
					<td align="center">
						<font color="red">《${b.b_bookname}》</font>&nbsp;&nbsp;&nbsp;
					</td>
					<td align="center">
						<font color="red">${b.b_author}</font>&nbsp;&nbsp;&nbsp;
					</td>
					<td align="center">
						<font color="red">${b.b_pubs}</font>&nbsp;&nbsp;&nbsp;
					</td>
					<td align="center">
						<font color="red">${b.b_price}</font>&nbsp;&nbsp;&nbsp;
					</td>
					<td align="center">
						<font color="red">${requestScope.cart.get(b)}</font>
					</td>
				</tr>
			</c:forEach>
		</table>
			<input type="hidden" name="bks" value="${requestScope.bks}" id="bks">
			<div style="margin-left:660px;">总计￥<font color="red">${requestScope.total}</font>元</div>
		</c:if>
		<br><br>
		<div style="margin-left:400px;">
			<p>请选择支付方式</p>
			<c:if test='${requestScope.total<=sessionScope.me.u_balance}'>
				<input type="radio" name="payway" value="account" id="bal">账户余额
			</c:if>
				<input type="radio" name="payway" value="alipay" id="alp">支付宝
			<br><br>
			<a href="#" id="pay" style="margin-left: 180px; text-decoration: none;"><font color="red" size="5">去结账</font></a>
		</div>
	</div>
	<br><br>

</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#pay").click(function(){
			var flag=$("[name=payway]:checked").val()
			if(flag==null){
				alert("请选择支付方式！")
			}else if(flag=="account"){
				var bks=$("#bks").val()
				window.location.href='/ebook/MakeManyOrders.ord?bks='+bks
			}else if(flag=="alipay"){
				window.location.reload();
			}
		})
		
	})
</script>
</html>