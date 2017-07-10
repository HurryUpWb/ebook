package service;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import beans.Me;
import utils.SendEmail;
import utils.Validator;
import utils.getModel;

public class RegisterService {
	/**
	 * 判断邮箱是否可注册
	 * @param email
	 * @return
	 * @throws JSONException
	 */
	public static String validateEmail(String email) throws JSONException{
		JSONObject jsonObject=new JSONObject();
		if(Validator.isEmail(email)){
			StringBuffer sb=new StringBuffer();
			sb.append("SELECT u_account FROM userinfo WHERE u_email=?");
			Me m=getModel.getMeByEmail(sb.toString(), email);
			if(m.getU_account()!=null && m.getU_account()!=""){
				jsonObject.append("msg","used");
			}else{
				jsonObject.append("msg","ok");
			}
		}else{
			jsonObject.append("msg","notemail");
		}
		return jsonObject.toString();
	}
	
	public static String doRegister(HttpServletRequest request){
		String rendstr="";
		String vcode=request.getParameter("validate");
		String scode=(String)request.getSession().getAttribute("code");
		if(vcode.equals(scode)){
			boolean b=SaveUser(request);
			if(!b)
				rendstr="success";
		}else{
			rendstr="failed";
		}
		return rendstr;
	}
	
	public static void getcode(String email,String code){
		SendEmail(email,code);
	}
	
	public static boolean SaveUser(HttpServletRequest request){
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String pass=request.getParameter("pass");
		StringBuffer sb=new StringBuffer();
		sb.append("INSERT INTO userinfo(u_account,u_pwd,u_name,u_email,u_telephone)");
		sb.append(" VALUES(?,?,?,?,?)");
		return getModel.SaveMe(sb.toString(),name,pass,name,email,phone);
	}
	
	public static void SendEmail(String email,String validateCode){
		StringBuffer sb=new StringBuffer("您本次注册的验证码是：<br>");
		sb.append("<font color='red'>"+validateCode+"</font>");
        //发送邮件  
        SendEmail.send(email, sb.toString());  
	}
}
