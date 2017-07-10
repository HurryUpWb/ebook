<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fastsearch</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#prve").click(function(){
			window.location.href='/ebook/fastfind.do?flag=prve'
		})
		$("#next").click(function(){
			window.location.href='/ebook/fastfind.do?flag=next'
		})
	})
</script>
</head>
<body>
	<div style="margin-left: 350px;">
		<form action="fastfind.do" method="post">
			<table>
				<tr>
					<td>书籍名：<input name="bname" id="bname"></td>
				</tr>
				<tr>
					<td><br>作&nbsp;&nbsp;&nbsp;者：<input name="author" id="author"></td>
				</tr>
				<tr>
					<td>
						<br>
						类&nbsp;&nbsp;别：
						<select name="btype" id="btype">
								<option value="all">全部</option>
			        		<c:forEach items="${requestScope.type}" var="type">
			        			<option value="${type.TYPENO}">${type.TYPEINFO}</option>
			        		</c:forEach>
		       			</select>
		       		</td>
				</tr>
				<tr>
					<td>
						<br>
						价&nbsp;&nbsp;格：
						<select name="cate" id="cate">
							<option value="less"><</option>
							<option value="lessequal"><=</option>
							<option value="equal">=</option>
							<option value="moreequal">>=</option>
							<option value="more">></option>
						</select>
						<input name="price" id="price">
					</td>
				</tr>
				<tr>
					<td><br>出版社：<input name="pubs" id="pubs"></td>
				</tr>
				<tr>
					<td><br><input type="submit" name="sub"  id="sub" value="查找" style="width: 180px; font-style: oblique; margin-left:65px;background-color: lightgreen;"></td>
				</tr>
			</table>
		</form>
	</div>
	<br><br>
	<hr width="900px;">
	<br>
	<div style="margin-left: 300px;">
		<c:if test="${requestScope.blist!=null && requestScope.blist.size()>0}">
			&nbsp;&nbsp;共计__<font color="orange">${sessionScope.page.totalRecNum}</font>__条相关记录,第__<font color="orange">${sessionScope.page.getIndex()}</font>__页,共__<font color="orange">${sessionScope.page.totalPageNum}</font>__页<br><br>
			<c:forEach items="${requestScope.blist}" var="b">
				<a href="/ebook/getOneBook.do?id=${b.b_id}" style="text-decoration:none;">《${b.b_bookname}》__${b.b_author}__${b.b_pubs}__${b.b_price}</a>
				<br><br>
			</c:forEach>
			<div style="margin-left:10px;">
				<c:if test="${sessionScope.page.HasPrve()}">
					<img alt="left" id="prve" src="images/left.png" style="width:60px;height:50px;">
					&nbsp;&nbsp;&nbsp;
				</c:if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${sessionScope.page.HasNext()}">
					<img alt="left" id="next" src="images/right.png" style="width:60px;height:30px;">
				</c:if>
			</div>
		</c:if>
		<c:if test="${requestScope.msg eq 'none'}">
			<font color="red">没有找到相关书籍！</font>
		</c:if>
	</div>
</body>
</html>