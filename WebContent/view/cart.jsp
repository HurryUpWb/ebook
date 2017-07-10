<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cart</title>
<style type="text/css">
	td{
		width: 140px;
		height:150px;		
	}
	.num{
		width: 180px;
	}
	#bnum{
		width: 30px;
	}
</style>
</head>
<body>
	<div style="margin-top: 100px; margin-left:110px;">
		<c:if test="${sessionScope.me.cart.getBookMap().size()>0}">
			<div style="margin-top:-60px; margin-left: 60px;">
				<input type="checkbox"  id="checkall" name="checkall">
				<font>全选</font>
				<input type="checkbox"  id="uncheckall" name="uncheckall">
				<font>反选</font>
				<button id="drop" style="margin-left: 610px; width: 110px; background-color:lightgreen;">删除</button>
			</div> 
			<hr width="1000px;" style="height: 1px; background-color: black; margin-left:-80px; margin-top:10px;">
			<table id="tb" cellpadding="0" cellspacing="0">
				<c:forEach items="${sessionScope.me.cart.getBookMap().keySet()}" var="cart">
						<tr>
							<td align="center">
								<input type="checkbox" id="chk" name="chk">
								<input type="hidden" id="bid" name="bid" value="${cart.b_id}">
								<input type="hidden" name="bnum" id="bnum" value="${sessionScope.me.cart.getBookMap().get(cart)}">
							</td>
							<td align="center"><img alt="img" src="${cart.b_pic}"></td>
							<td align="center">
								《${cart.b_bookname}》<br>
								${cart.b_pubs}
							</td>
							<td align="center">${cart.b_author}</td>
							<td align="center">￥${cart.b_price}</td>
							<td class="num" align="center" id="num">
								<button id="${cart.b_id}down">&nbsp;-&nbsp;</button>
								<input name="bnum" id="bnum" value="${sessionScope.me.cart.getBookMap().get(cart)}">
								<button id="${cart.b_id}up">&nbsp;+&nbsp;</button>
							</td>
						</tr>
				</c:forEach>
			</table>
			<div style="margin-left:730px;">
				<input type="button" id="submitall" value="去付款" style="width:120px;height:30px;background-color: red;">
			</div>
		</c:if>
	</div>
	<div style="margin-left:450px;">
		<c:if test="${sessionScope.me.cart.getBookMap().size()==0}">
			<img alt="attention" style="height:130px;height:130px;" src="/ebook/images/attention.png">
			<div style="margin-top:15px;">
				<font color="#000000" style="font-style: oblique;">没有已经选购的书籍！去看看吧~</font>
			</div>
		</c:if>
	</div>
</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#checkall").click(function(){
			var flg=$(this).attr("checked")
			$("[name=chk]").attr("checked",flg=="checked")
		})
		$("#uncheckall").click(function(){
			$("[name=chk]").each(function(){
				var flg=$(this).attr("checked")
				$(this).attr("checked",!(flg=="checked"))
			})
		})
		$("#drop").click(function(){
			$("[name=chk]").each(function(){
				var flag=$(this).attr("checked")
				if(flag=="checked"){
					var bid=$(this).next().attr("value")
					$.ajax({
						type:'post',
						url:'/ebook/DropfromCart.usr?bid='+bid,
						async:true,
						dataType:'json',
						success:function(data){
							if(data.msg=='ok'){
								//alert(data)
								$(this).parent().parent().remove()
								window.location.reload();
							}
							if(data.msg=='fail'){
								alert('删除失败！')
							}
							window.location.reload();
						},
						error:function(){
							//alert('system error!')
						}
					})
				}
			})
		})
		
		var bds=$("#tb").find("[name=bid]")
		bds.each(function(){
			var id=$(this).val()
			
			$("#"+id+"down").click(function(){
				var num=parseInt($(this).next().val())
				if(num>1){
					$(this).next().attr("value",num-1)
				}else{
					$(this).attr("disabled","disabled")
				}
				var nums=$(this).next().val()
				$.ajax({
						type:'post',
						async:true,
						url:'/ebook/AddtoCart.usr?bid='+id+"&num="+nums+"&flag=add",
						dataType:'json',
						success:function(data){
								
						},
						error:function(){
							alert("system error!")
						}
				})
			})
			
			$("#"+id+"up").click(function(){
				var num=parseInt($(this).prev().val())
				$(this).prev().attr("value",num+1)
				var nums=$(this).prev().val()
				$.ajax({
						type:'post',
						async:true,
						url:'/ebook/AddtoCart.usr?bid='+id+"&num="+nums+"&flag=add",
						dataType:'json',
						success:function(data){
								
						},
						error:function(){
							alert("system error!")
						}
				})
			})
			
		})
		
		$("#submitall").click(function(){
			var str="";
				$("[name=chk]").each(function(){
					var flag=$(this).attr("checked")
					if(flag=='checked'){
						str=str+","+$(this).next().val()
					}
				})
			if(str==""){
				alert("请选择需要付款的书籍！")
				return 
			}else{
				//window.location.href='/ebook/MakeManyOrders.ord?bks='+str
				window.location.href='/ebook/MakeManyAccount.ord?bks='+str
			}
		})
	})
</script>
</html>