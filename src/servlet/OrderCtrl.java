package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Adress;
import beans.Book;
import beans.Cart;
import beans.Comments;
import beans.Me;
import beans.Order;
import service.UserOrderService;
import service.UsrService;
import utils.MyUtils;
import utils.getModel;

public class OrderCtrl extends HttpServlet{
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
	private void MakeOneAccount(HttpServletRequest request,HttpServletResponse response){
		String bid=request.getParameter("bid");
		String num=request.getParameter("num");
		Me m=(Me)request.getSession().getAttribute("me");
		int uid=m.getU_id();
		try {
			if(!MyUtils.IsNull(bid) && !MyUtils.IsNull(num) && uid!=0){
				Book b=getModel.getBookById(Integer.parseInt(bid));
				double balance=b.getB_price()*Integer.parseInt(num);
				List<Adress> list=UsrService.getAdress(uid);
				request.setAttribute("book",b);
				request.setAttribute("number", num);
				request.setAttribute("balance",balance);
				request.setAttribute("adrlist",list);
				request.getRequestDispatcher("/view/account/MakeAccount.jsp").forward(request, response);
			}else{
				response.sendRedirect("/ebook");
			}
		} catch (NumberFormatException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void MakeManyAccount(HttpServletRequest request,HttpServletResponse response){
		String bks=request.getParameter("bks");
		String[] bk=bks.split(",");
		List<Book> list=new ArrayList<>();
		Me m=(Me)request.getSession().getAttribute("me");
		double db=0;
		for(String str:bk){
			if(str!=null && !str.equals("")){
				Book b=getModel.getBookById(Integer.parseInt(str));
				list.add(b);
				db=db+(m.getCart().getBookMap().get(b)*b.getB_price());
			}
		}
		request.setAttribute("bks",bks);
		request.setAttribute("total",db);
		request.setAttribute("cart",m.getCart().getBookMap());
		request.setAttribute("blist",list);
		try {
			request.getRequestDispatcher("/view/account/MakeBooksAccount.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void MakeOrder(HttpServletRequest request,HttpServletResponse response){
		boolean b=UserOrderService.OrderByOneBook(request);
		String path="";
		String msg="";
		if(!b){
			//转去扣除balance方法，就算成功
			Me m=(Me)request.getSession().getAttribute("me");
			Cart cart=m.getCart();
			List<Order> list=UserOrderService.getOrderByUid(m.getU_id());
			Order o=null;
			if(list!=null && list.size()>0){
				o=list.get(0);
			}
			boolean flag=UserOrderService.UpdateBalance(m,o.getO_totalprice());
			if(!flag){
				UserOrderService.UpdatePayMent(o.getO_id());
				Me newMe=UsrService.getMe(m.getU_id());
				if(newMe!=null){
					newMe.setCart(cart);
					request.getSession().setAttribute("me",newMe);
				}
				msg="支付成功！";
				path="/view/account/success.jsp";
			}else{
				msg="扣款失败！";
				path="/view/account/error.jsp";
			}
		}else{
			msg="生成订单失败！";
			path="/view/account/error.jsp";
		}
		request.setAttribute("msg", msg);
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private  void MakeManyOrders(HttpServletRequest request,HttpServletResponse response){
		double db=UserOrderService.OrderByManyBooks(request);
		String bks=request.getParameter("bks");
		String path="";
		String msg="";
		if(db!=0){
			//转去扣除balance方法，就算成功
			Me m=(Me)request.getSession().getAttribute("me");
			List<Order> list=UserOrderService.getOrderByUid(m.getU_id());
			Order o=null;
			if(list!=null && list.size()>0){
				o=list.get(0);
			}
			boolean flag=UserOrderService.UpdateBalance(m,db);//更新用户账户余额
			UserOrderService.DeleteBookFromCart(m, bks);//从购物车中删除已经购买的
			if(!flag){
				UserOrderService.UpdatePayMent(o.getO_id());
				Me newme=UsrService.getMe(m.getU_id());
//				m.setCart(UsrService.UserCartToCart(UsrService.getCart(m.getU_id())));
//				if(m.getCart()==null){
//					m.setCart(new Cart());
//				}
				request.getSession().setAttribute("me",newme);
				msg="支付成功！";
				path="/view/account/success.jsp";
			}else{
				msg="扣款失败！";
				path="/view/account/error.jsp";
			}
		}else{
			msg="生成订单失败！";
			path="/view/account/error.jsp";
		}
		request.setAttribute("msg", msg);
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getOrder(HttpServletRequest request,HttpServletResponse response){
		Me m=(Me)request.getSession().getAttribute("me");
		String flag=request.getParameter("flag");
		String path="";
		List<Order> list=null;
		if(m!=null){
			path="/view/account/showOrder.jsp";
			list=UserOrderService.getOrder(request,m.getU_id(),flag);
		}
		request.setAttribute("olist",list);
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void ToComment(HttpServletRequest request,HttpServletResponse response){
		String oid=request.getParameter("oid");
		List<Book> list=null;
//		Me m=(Me)request.getSession().getAttribute("me");
//		Integer uid=m.getU_id();
//		List<Comments> clist=UserOrderService.getCommentBid(uid);
		if(oid!=null && !oid.equals("")){
			list=UserOrderService.getOrderBooks(Integer.parseInt(oid));
		}
		request.setAttribute("blist",list);
		String path="/view/account/Comment.jsp";
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void SaveComment(HttpServletRequest request,HttpServletResponse response){
		String comm=request.getParameter("comment");
		String comment="";
		try {
			comment=new String(comm.getBytes("iso8859-1"),"utf8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String bid=request.getParameter("bid");
		Me m=(Me)request.getSession().getAttribute("me");
		boolean b=UserOrderService.SaveComment(m.getU_id(),Integer.parseInt(bid),comment);
		try {
			request.getRequestDispatcher("getOrder.ord").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
