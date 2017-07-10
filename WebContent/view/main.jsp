<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String basepath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/css/my.css" rel="stylesheet"> 
<title>ebook</title>
</head>

<body style=" overflow: hidden;background-image:url(images/timg.jpg);">
<!-- 米米服装_mimiCustome -->
	<h1>ebook&nbsp;&nbsp;<font size="3" color="#006400" style="font-family:cursive;">读史使人明智,读诗使人灵秀,数学使人周密,科学使人深刻,伦理学使人庄重 </font></h1>
	<div style="width: 50px; height: 40px; margin-left:1090px; margin-top: -30px;">
		<img alt="user" src="/ebook/images/user.png" style="width: 45px;height:45px;">
		<div style="margin-top: -25px; margin-left:50px; width: 80px;">
			<c:if test="${sessionScope.me!=null}">
				<a href="#">${sessionScope.me.u_account}</a>
			</c:if>
			<c:if test="${sessionScope.me==null}">
				<a href="#" id="login">Login</a>
			</c:if>
		</div>
		<img alt="shopcar" id="shopcar" src="/ebook/images/shopcar.png" style="width: 100px;height:90px;margin-top: -65px;margin-left:90px;">
		<input type="hidden" name="usr" id="usr" value="${sessionScope.me.u_id}">
		<c:if test="${sessionScope.me!=null}">
			<img alt="logout" id="logout" src="/ebook/images/logout.png" style="width:45px;height:45px;margin-top: -65px;margin-left:190px;">
		</c:if>
	</div>
    <hr width="1420" style="height: 2px; background-color: black; margin-top:11px;">
    <div class="leftmenu" style="width:200px;background-image:url(images/timg.jpg); border:1px; border-left:2px; height: 550px;">
	    	<ul>
	    		<br><br>
	    		<li><a href="#" id="front"><font>首页</font></a></li>
	    		<br><br>
	    		<li><a href="#" id="recommend"><font>新上架</font></a></li>
	    		<br><br>
	    		<li><a href="#" id="fastsearch"><font>条件查找</font></a></li>
	    		<br><br>
	    		<li><a href="#" id="types"><font>分类查找</font></a></li>
	    		<br><br>
	    		<li><a href="#" id="my"><font>我的</font></a></li>
	    		<br><br>
	    		<!-- <li><a href="#" id=""><font>给我们留言</font></a></li> -->
	    		<li><a href="#" id="show"><font>网站介绍</font></a></li>
	    	</ul>
	    	<hr size=565 width="1" color="black" style="margin-top: -440px; margin-left: 140px;">
    </div>
    <iframe class="ifr" frameborder="0" id="ifr" src="<%=basepath%>/view/front.jsp">
    	 
    </iframe>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#recommend").click(function(){
			$("#ifr").attr("src","<%=basepath%>/view/showlast.jsp");
		})
		
		$("#front").click(function(){
			$("#ifr").attr("src","<%=basepath%>/view/front.jsp");
		})
		
		$("#types").click(function(){
			$("#ifr").attr("src","<%=basepath%>/getCategory.do");
		})
		
		$("#fastsearch").click(function(){
			$("#ifr").attr("src","<%=basepath%>/getCategory.do?page=fastsearch");
		})
		
		$("#my").click(function(){
			$("#ifr").attr("src","<%=basepath%>/view/Mine.jsp");
		})
		
		$("#show").click(function(){
			$("#ifr").attr("src","<%=basepath%>/view/about.html");
		})
		
		
		$("#logout").click(function(){
			var flg=confirm("确定注销？")
			if(flg){
				$.ajax({
					type:'post',
					url:'<%=basepath%>/logout.usr',
					dataType:'json',
					success: function(msg){
						if(msg.msg=='success')
							parent.location.reload();
						else
							alert('注销失败')
					},
					error: function(){
						alert('system error!')
					}
				})
			}
		})
		
		$("#login").click(function(){
			$("#ifr").attr("src","<%=basepath%>/view/Mine.jsp");
		})
		
		$("#shopcar").click(function(){
			var flag=$("#usr").val();
			if(flag==""){
				 alert("未登录， 请先登录 !")
				 $("#ifr").attr("src","<%=basepath%>/view/login.jsp");
			}else{
				$("#ifr").attr("src","<%=basepath%>/view/cart.jsp");
			}
		})
	})
</script>
</html>