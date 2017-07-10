package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import beans.Book;
import beans.Cart;
import beans.Me;
import beans.UserCart;
import service.UsrService;
import utils.MyUtils;

public class UserCtrl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
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
	
	@SuppressWarnings({ "unused" })
	private void getUsr(HttpServletRequest request,HttpServletResponse response) throws JSONException{
			String uname=request.getParameter("name");
			String pwd=request.getParameter("pwd");
			String msg="";
			JSONObject jsonObject=new JSONObject();
			if(!MyUtils.IsNull(uname)&& !MyUtils.IsNull(pwd)){
				Me m=UsrService.getMe(uname, pwd);
				if(!MyUtils.IsNull(m) && !MyUtils.IsNull(m.getU_id()) && !MyUtils.IsNull(m.getU_account())){
					Cart cart=null;
					UserCart userCart=UsrService.getCart(m.getU_id());
					if(userCart.getBooks()!=null && userCart.getU_id()!=0){
						cart=UsrService.UserCartToCart(UsrService.getCart(m.getU_id()));
					}
					if(cart!=null){
						m.setCart(cart);
					}
					request.getSession().setAttribute("me",m);
					jsonObject.append("msg","success");
				}else{
					jsonObject.append("msg","no record");
				}
			}else{
				jsonObject.append("msg","no input");
			}
			try {
				response.getWriter().write(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unused")
	private void logout(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		request.getSession().removeAttribute("me");
		JSONObject json=new JSONObject();
		if(request.getSession().getAttribute("me")==null){
			json.append("msg","success");
		}else{
			json.append("msg", "failed");
		}
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void AddtoCart(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		String msg=UsrService.AddtoCart(request);
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void DropfromCart(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		String bid=request.getParameter("bid");
		Me me=(Me)request.getSession().getAttribute("me");
		try {
			response.getWriter().write(UsrService.DropfromCart(me,Integer.parseInt(bid)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
