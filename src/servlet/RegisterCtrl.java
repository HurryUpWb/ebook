package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import service.RegisterService;
import utils.MyUtils;

public class RegisterCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getServletPath();
		String n=method.substring(1);
		String m=n.substring(0,n.length()-4);
		
		try {
			Method ms=getClass().getDeclaredMethod(m, HttpServletRequest.class,HttpServletResponse.class);
			ms.invoke(this,request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unused")
	private void Register(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String str=RegisterService.doRegister(request);
		String render="";
		if(str.equals("success")){
			render="/ebook/view/register/info.jsp";
		}else if(str.equals("failed")){
			render="/ebook/view/register/failed.jsp";
		}
		try {
			response.sendRedirect(render);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void getValidateCode(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		String email=request.getParameter("email");
		String code=MyUtils.getRandomStr();
		RegisterService.getcode(email, code);
		request.getSession().setAttribute("code",code);
		try {
			jsonObject.append("msg","success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void ValidateEmail(HttpServletRequest request,HttpServletResponse response){
		String email=request.getParameter("email");
		String msg="";
		try {
			msg=RegisterService.validateEmail(email);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
