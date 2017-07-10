package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Book;
import service.Putview;
import utils.MyPage;
import utils.MyUtils;

public class Transmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getServletPath();
		String n=method.substring(1);
		String m=n.substring(0,method.length()-4);
		try {
			Method ms=getClass().getDeclaredMethod(m, HttpServletRequest.class,HttpServletResponse.class);
			ms.invoke(this,request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void getLastBooks(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf8");
		response.setContentType("text/html ; charset=UTF-8");
		 try {
			response.getWriter().write(Putview.getShowLastBooks(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void getCategory(HttpServletRequest request,HttpServletResponse response){
		String page=request.getParameter("page");
		request.setAttribute("type",Putview.getTypes());
		String path="";
		if(!MyUtils.IsNull(page) && "fastsearch".equals(page)){
			path="/view/fastsearch.jsp";
		}else{
			path="/view/allcatagories.jsp";
		}
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void getOneBook(HttpServletRequest request,HttpServletResponse response){
		String bid=request.getParameter("id");
		if(bid!=null & bid!=""){
			request.setAttribute("book",Putview.getOneBook(Integer.parseInt(bid)));
			request.setAttribute("comm",Putview.getComments(Integer.parseInt(bid)));
		}else{
			request.setAttribute("msg","error");
		}
		try {
			request.getRequestDispatcher("/view/showonebook.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unused")
	private void fastfind(HttpServletRequest request,HttpServletResponse response){
		List<Book> list=null;
		try {
			request.setCharacterEncoding("utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MyPage sessionPage =null;
		MyPage pg=null;
		String flag=request.getParameter("flag");
		if(!MyUtils.IsNull(flag)){
			sessionPage=(MyPage)request.getSession().getAttribute("page");
			if("prve".equals(flag))
				sessionPage.setIndex(sessionPage.getIndex()-1);
			if("next".equals(flag))
				sessionPage.setIndex(sessionPage.getIndex()+1);
			list=sessionPage.SwchPage();
			request.getSession().setAttribute("page",sessionPage);
		}else{
			pg=Putview.getBooks(request);
			if(!MyUtils.IsNull(pg)){
				request.getSession().setAttribute("page",pg);
				list=pg.SwchPage();
			}
		}
		
		if(list!=null && list.size()>0){
			request.setAttribute("type",Putview.getTypes());
			request.setAttribute("blist",list);
		}else{
			request.setAttribute("type",Putview.getTypes());
			request.setAttribute("msg","none");
		}
		
		try {
			request.getRequestDispatcher("/view/fastsearch.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void findByCategory(HttpServletRequest request,HttpServletResponse response){
		String typeno=request.getParameter("typeno");
		List<Book> list=Putview.getBookByType(typeno);
		request.setAttribute("blist",list);
		try {
			request.getRequestDispatcher("/view/showbookbbycate.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
