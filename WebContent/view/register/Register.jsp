<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basepath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<link href="/ebook/css/styles.css" rel="stylesheet" />
</head>
<body>
	<div id="carbonForm">
    <h2>ebook注册</h2>
    <a  href="#" id="login" style="margin-left: 350px;">已有账号，去登录</a>
    <br>
    <form action="#" method="post" id="signupForm" onsubmit="return ">
    <div class="fieldContainer">
      <div class="formRow">
        <div class="label">
          <label for="email">邮箱:</label>
        </div>
        <div class="field">
          <input type="text" name="email" id="email" />
        </div>
      </div>
      
       <div class="formRow">
        <div class="label">
          <label for="name">用户名:</label>
        </div>
        <div class="field">
          <input type="text" name="name" id="name" />
        </div>
      </div>
      
       <div class="formRow">
        <div class="label">
          <label for="name">手机号:</label>
        </div>
        <div class="field">
          <input type="text" name="phone" id="phone" />
        </div>
      </div>
      
      <div class="formRow">
        <div class="label">
          <label for="pass">密码:</label>
        </div>
        <div class="field">
          <input type="password" name="pass" id="pass" />
        </div>
      </div>
      
      <div class="formRow">
        <div class="label">
          <label for="pass">确认密码:</label>
        </div>
        <div class="field">
          <input type="password" name="repass" id="repass" />
        </div>
      </div>
      
       <div class="formRow">
        <div class="label">
          <label for="pass">验证码:</label>
        </div>
        <div class="field">
          <input type="text" name="validate" id="validate" />
          &nbsp;&nbsp;<a href="#" id="getcode">获取验证码</a>
        </div>
      </div>
    </div>
    <!-- Closing fieldContainer -->
    <div class="signupButton">
      <input type="submit" name="submit" id="submit" value=""/>
    </div>
    </form>
  </div>
	
</body>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#login").click(function(){
			window.location.href="/ebook/view/login.jsp"
		})
		
		$("#getcode").click(function(){
			var email=$("#email").val()
			if(email==""){
				alert("请先输入邮箱！")
				return
			}else{
				$.ajax({
					type:'post',
					url:'/ebook/getValidateCode.reg?email='+email,
					dataType: "json",
					success:function(data){
						alert("邮件已发送！请查收")
					},
					error:function(){
						
					}
				})
			}
		})
		
		$("#phone").change(function(){
			var mobile=$("#phone").val()
			//var phone=/^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$/
			var phone=/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
			//var flag=mobile.match(phone)
			var flag=phone.test(mobile)
			if(flag==false){
				alert("请输入正确格式的手机号！")
				$("#phone").val("").focus()				
			}
		})
		
		$("#submit").click(function(){
			var code=$("#validate").val()
			if(code==""){
				alert("请输入验证码！")
			}else{
				$("#signupForm").attr("action","/ebook/Register.reg").submit()
			}
		})
		
		$("#repass").change(function(){
			var pass=$("#pass").val()
			var repass=$("#repass").val()
			if(pass!=repass){
				alert("两次输入的密码不一致！")
				$("#pass").val("").focus()
				$("#repass").val("").focus()
			}
		})
		
		$("#email").change(function(){
			var email=$("#email").val()
			var reg="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$"
			var flag=email.match(reg)
			if(flag){
				$.ajax({
					type:'post',
					url:'/ebook/ValidateEmail.reg?email='+email,
					dataType: "json",
					success:function(data){
						if(data.msg=='used'){
							$("#email").val("").focus()
							alert("该邮箱已被使用！")
						}
					},
					error:function(){
						alert("system error!")
					}
				})
			}else{
				alert("请输入正确格式的邮箱！")
				$("#email").val("").focus()
			}
		})
		
	})
	
	
</script>
</html>