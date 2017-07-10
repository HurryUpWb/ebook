<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin-top: 100px; margin-left:30px;">
		<img id="back" alt="img" src="images/backspace.png" style="width:90px;height:30px;">
	</div>
	<div style="margin-top: 10px; margin-left:130px;">
		<c:if test="${requestScope.blist!=null && requestScope.blist.size()>0}">
			<c:forEach items="${requestScope.blist}" var="b">
				<form action="/ebook/SaveComment.ord" method="post">
					<font color="red">《${b.b_bookname}》</font>
						--<font color="green">${b.b_author}</font>
							--<font color="orange">${b.b_pubs}</font><br>
					<font size="1px;" style="margin-left:250px;">写评论，不超过100字</font><br>
					<textarea id="comment" name="comment" rows="7" cols="50" style="resize:none;"></textarea>
					<input type="hidden" name="bid" id="bid" value="${b.b_id}">
					<input type="submit" name="sub" id="sub" value="评价">
					<br><br>	
				</form>
			</c:forEach>
		</c:if>	
		<c:if test="${requestScope.blist==null || requestScope.blist.size()==0}">
				<font color="red">您已经做过了评价！</font>
		</c:if>
	
	</div>
</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$("#back").click(function(){
		window.location.href=history.go(-1)
	})
</script>
</html>