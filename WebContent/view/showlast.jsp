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
<title>Insert title here</title>
<link type="text/css" rel="Stylesheet" href="<%=basepath%>/css/imageflow.css" />
<script type="text/javascript" src="<%=basepath%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=basepath%>/js/imageflow.js"></script>

<script type="text/javascript">
	var urls="/ebook/getLastBooks.do"
	$(document).ready(function(){
		$.ajax({
			type:'post',
			url:urls,
		    dataType:'json', 
			success:function(data){
				var str=data
				for(var i=1,l=str.length;i<l;i++){
					for(var key in str[i]){
						if(key=="pic"){
							$(".p"+i).attr("src",str[i][key])
							
						}
						if(key=="name"){
							$(".p"+i).attr("title",str[i][key])
						}
						if(key=="id"){
							$(".p"+i).attr("name",str[i][key])
						}
					}
				}
				$("img").click(function(){
					var id=$(this).attr("name")
					window.location.href='/ebook/getOneBook.do?id='+id;
				});
			},
			error:function(){
				alert("system error! ")
			}
		});
		
		
	})
</script>
</head>
<body style="background-image:url(../images/timg.jpg); overflow: hidden;">
<!-- 轮播图 -->
	<div id="LoopDiv">
		<input id="S_Num" type="hidden" value="8" />
		<div id="starsIF" class="imageflow" style="margin-left:-110px;"> 
			<img src="<%=basepath%>/images/1.png" class="p1" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
			<img src="<%=basepath%>/images/2.png" class="p2" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
			<img src="<%=basepath%>/images/3.png" class="p3" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
			<img src="<%=basepath%>/images/4.png" class="p4" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
			<img src="<%=basepath%>/images/4.png" class="p5" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
			<img src="<%=basepath%>/images/4.png" class="p6" longdesc="#" width="280" height="280" alt="Picture" title="" name="">
		</div>
	</div>

</body>
</html>