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
	<div style="margin-left:25px; " id="scrollDiv" class="content">
			<c:if test="${requestScope.blist!=null && requestScope.blist.size()>0}">
				<c:forEach items="${requestScope.blist}" var="b">
					<div style="float:left; width:400px;height:250px;">
						<img name="showonebook" alt="bimg" id="bimg" src="${b.b_pic_large}" width="160px;" height="200px;">
						<input type="hidden" name="bid" id="bid" value="${b.b_id}">
						<div style="margin-top: -170px; margin-left: 190px;">
							<font>
								<a href="#" name="showonebook" style="text-decoration: none;">《${b.b_bookname}》</a>
								<input type="hidden" name="bid" id="bid" value="${b.b_id}">
							</font><br>
							<font>&nbsp;&nbsp;${b.b_author}</font><br>
							<font>&nbsp;&nbsp;${b.b_pubs}</font><br>
							<textarea rows="7" cols="25" readonly="readonly"
								style="overflow:hidden;background-image:url(images/timg.jpg);border:0px; resize:none;">
									${b.b_description}
							</textarea>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<br><br><br><br><br><br>
				<c:if test="${requestScope.blist.size()==0}">
					<img alt="attention" style="height:130px;height:130px;" src="/ebook/images/attention.png">
					<div style="margin-top:15px;">
						<font color="#000000" style="font-style: oblique;">此分类还没有书哦！去看看其他的吧~</font>
					</div>
				</c:if>
	</div>
</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
function scroll(){
	   $(".content").animate({"margin-top":"0px"},function(){
     	$(".content div:eq(0)").appendTo($(".content"))
	     $(".content").css({"margin-top":0})
	   })
	}
setInterval(scroll,5000)
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("[name=showonebook]").click(function(){
			var bid=$(this).next().val()
			parent.location.href='/ebook/getOneBook.do?id='+bid
		})
	})
</script>
</html>