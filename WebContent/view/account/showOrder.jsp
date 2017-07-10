<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		height:50px;
	}
</style>
</head>
<body style="overflow: auto;">
	<div style="background-color:lightgreen; margin-top:100px; margin-left: 20px;">
		<c:if test="${requestScope.olist!=null && requestScope.olist.size()>0}">
			<table>
				<tr>
					<th align="center">编号</th>
					<th align="center">总数</th>
					<th align="center">名称</th>
					<th align="center">时间</th>
					<th align="center">总价格</th>
					<th align="center">付款</th>
					<th align="center">发货</th>
				</tr>
				<c:forEach items="${requestScope.olist}" var="ord">
					<tr>
						<td align="center">${ord.o_id}</td>
						<td align="center">${ord.o_booknum}</td>	
						<td align="center">${ord.o_booksname}</td>		
						<td align="center">${ord.o_orderdate}</td>
						<td align="center">${ord.o_totalprice}</td>
						<td align="center">${ord.p_state==1?'已付':'未付'}</td>
						<td align="center">${ord.o_isdeliver==1?'是':'否'}</td>
						<td align="center">
							<a href="#" id="comment" name="comment">评价</a>
							<input type="hidden" name="oid" id="oid" value="${ord.o_id}">
						</td>
					</tr>			
				</c:forEach>
			</table>
		</c:if>
	</div>
	<div style="margin-left:250px; margin-top:230px;">
		<c:if test="${(requestScope.flag==null || requestScope.flag=='') && requestScope.olist.size()==0}">
			<img alt="attention" style="height:130px;height:130px;" src="/ebook/images/attention.png">
			<font size="4">没有购买历史！</font>
		</c:if>
	</div>
	<div style="margin-left:250px; margin-top:230px;">
		<c:if test="${requestScope.flag eq 'fail' }">
			<img alt="attention" style="height:130px;height:130px;" src="/ebook/images/attention.png">
			<font size="4">没有未完成订单！</font>
			
		</c:if>
	</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("[name=comment]").click(function(){
			//alert($(this).next().val())
			var oid=$(this).next().val()
			window.location.href='/ebook/ToComment.ord?oid='+oid
		})
	})
</script>
</html>