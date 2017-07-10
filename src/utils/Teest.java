package utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Teest {
	
	@Test
	public void test2(){
		Date date=new Date();
		Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=format.format(date);
		System.out.println(str);
	}
	
	@Test
	public void test() {
		StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！<br>");  
        sb.append("<a href=\"http://localhost:8080/Register.reg?action=activate&email=");  
        sb.append("838033379@qq.com");   
        sb.append("&validateCode=");   
        sb.append("1234555");  
        sb.append("\">http://localhost:8080/Register.reg?action=activate&email=");   
        sb.append("838033379@qq.com");  
        sb.append("&validateCode=");  
        sb.append("1234555");  
        sb.append("</a>");  
          
        //发送邮件  
        SendEmail.send("wbdyx01@163.com", sb.toString());  
        System.out.println("发送邮件");  
	}

}
