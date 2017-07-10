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
<title>showonebook</title>
<style type="text/css">
	font{
		font-size: 20px;
		font-style: oblique;
	}
</style>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#bt1").click(function(){
			var num=parseInt($("#num").attr("value"))
			if(num>1){
				$("#num").attr("value",num-1)
			}else{
				$(this).attr("disabled","disabled")
			}
		})
		
		$("#bt2").click(function(){
			var num=parseInt($("#num").attr("value"))
			$("#num").attr("value",num+1)
		})
		
		$("#back").click(function(){
			window.location.href=history.go(-1)
		})
		
		$("#Add").click(function(){
			$.ajax({
				type:'post',
				url:'/ebook/AddtoCart.usr?bid='+$("#bid").val()+'&num='+$("#num").val(),
				dataType: 'json',
				success: function(data){
					if(data.msg=='success'){
						alert('添加成功！')
					}
					if(data.msg=='nologin'){ 
						alert('请先登录！')
					}
					if(data.msg=='norecord'){
						alert('没有对应记录')
					}
				},
				error: function(){
					alert('请先登录!')
				}
			})	
		})
		
		$("#buynow").click(function(){
			var bid=$("#bid").val()
			var num=$("#num").val()
			window.location.href="/ebook/MakeOneAccount.ord?bid="+bid+"&num="+num
		})
	})
</script>
</head>
<body style="background-image:url(images/timg.jpg);">	
	<img id="back" alt="img" src="images/back.png" style="width: 60px;height: 30px;">
	<div style="margin-top:50px; margin-left: 100px;">
		<c:if test="${requestScope.book!=null}">
				<input type="hidden" name="bid" id="bid" value="${requestScope.book.b_id}">
				<img alt="img" src="${requestScope.book.b_pic_large}" height="357px;" width="250px;"><br>
				<div id="info" style="margin-left: 300px; margin-top: -330px;">
					<font>书籍名称：</font>&nbsp;&nbsp;《${requestScope.book.b_bookname}》
					<br><br>
					<font>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：</font>&nbsp;&nbsp;&nbsp;${requestScope.book.b_author}
					<br><br>
					<font>出&nbsp;&nbsp;版&nbsp;社：</font>&nbsp;&nbsp;&nbsp;${requestScope.book.b_pubs}
					<br><br>
					<font>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</font>&nbsp;&nbsp;&nbsp;${requestScope.book.b_price}
					<br><br>
					<font>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</font>
					 <textarea readonly="readonly" rows="3px" cols="30px;" style="background-image:url(images/timg.jpg);border: none;">${requestScope.book.b_description}</textarea>
					<br><br>
					<font>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</font>
						<button id="bt1">-&nbsp;</button><input id="num" name="num" value="1" size="1px;"><button id="bt2">+</button>
						<br><br>
					<c:if test="${sessionScope.me!=null}">
						<button type="button" id="buynow"><font style="font-family: fantasy; font-weight:lighter;">立即购买</font></button>&nbsp;&nbsp;
					</c:if>
					<button type="button" id="Add"><font style="font-family: fantasy; font-weight:lighter;">添加到购物车</font></button>
				</div>
				<br><br>
				<hr width="1420" style="height: 2px; background-color: black; margin-left:-500px;">
				<div style="margin-left: -100px;">
					<font>相关评论：</font>总计&nbsp;<font color="#0000FF">${requestScope.comm.size()}</font>&nbsp;条<br>
					<c:if test="${requestScope.comm!=null && requestScope.comm.size()>=1}">
						<div style="margin-left: 80px;">
							<c:forEach items="${requestScope.comm}" var="com">
								用户：<font color="orange">${com.u_name}</font>&nbsp;&nbsp;&nbsp;&nbsp;于：<font color="green">${com.time}</font><br><br>
								&nbsp; ---- &nbsp;<font color="red">${com.content}</font>
								<br><br><br>
							</c:forEach>
						</div>
					</c:if>
				</div>
		</c:if>
		<c:if test="${requestScope.msg eq 'error'}">
			<font color="red">没找到相关书籍</font>
		</c:if>
	</div>
</body>
</html>