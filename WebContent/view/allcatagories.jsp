<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>allcatagories</title>
</head>
<body style="background-image:url(images/timg.jpg); overflow: hidden;">
	<div style="width:350px;">
		<c:if test="${requestScope.type!=null}">
	   		<!-- <font style="font-family: cursive; margin-left: 50px;">当前所有分类</font>
	   		<hr width="1280" align="left"> -->
	   		<ul>
		    	<c:forEach items="${requestScope.type}" var="t">
		    			<li>
		    				<a href="#"  name="type">${t.TYPEINFO}</a>
		    				<input type="hidden" name="typeno" id="typeno" value="${t.TYPENO}">
		    			</li>
		    	</c:forEach>
	    	</ul>
	   	</c:if>
	   	<c:if test="${requestScope.type==null}">
	   		<font color="red">没有分类</font>
	   	</c:if>
   	</div>
   	<iframe id="ifr" height="760px;" width="1000px;" src="" style=" margin-top: -510px; margin-left:350px; border: none;">
   		
   	</iframe>
</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("[name=type]").click(function(){
			var typeno=$(this).next().val()
			$("#ifr").attr("src","/ebook/findByCategory.do?typeno="+typeno)
		})
	})
</script>
</html>