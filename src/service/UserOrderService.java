package service;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import beans.Book;
import beans.Cart;
import beans.Comments;
import beans.Me;
import beans.Order;
import utils.getModel;

public class UserOrderService {
	
	/**
	 * 一本书生成订单
	 * @param request
	 * @return
	 */
	public static boolean OrderByOneBook(HttpServletRequest request){
		Me m=(Me)request.getSession().getAttribute("me");
		//String bname=request.getParameter("bname");
		String bid=request.getParameter("bid");
//		try {
//			bname=new String(request.getParameter("bname").getBytes("iso-8859-1"),"utf8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		String bnum=request.getParameter("bnum");
		String balance=request.getParameter("balance");
		return buildOrder(bid,m.getU_id(),Integer.parseInt(bnum),Double.parseDouble(balance));
	}
	/**
	 * 购物车生成订单
	 * @param request
	 * @return
	 */
	public static double OrderByManyBooks(HttpServletRequest request){
		String bks=request.getParameter("bks");
		Me m=(Me)request.getSession().getAttribute("me");
		int uid=m.getU_id();
		Cart cart=m.getCart();
		String booksname="";
		int num=0;
		double balance=0;
		String[] bookid=bks.split(",");
		for(String str:bookid){
			if(str!=null && !str.equals("")){
				Book book=getModel.getBookById(Integer.parseInt(str));
//				booksname=booksname+","+book.getB_bookname();
				double price=book.getB_price();
				int	bnums=cart.getBookMap().get(book).intValue();
				balance=balance+(price*bnums);
				num=num+bnums;
				//boolean b=buildOrder(book.getB_bookname(),uid,cart.getBookMap().get(book),book.getB_price());
			}else{
				continue;
			}
		}
		boolean b=buildOrder(bks,uid,num,balance);
		if(b){
			return 0;
		}else{
			return balance;
		}
	}
	
	/**
	 * 从购物车中删除
	 * @param m
	 * @param bks
	 */
	public static void DeleteBookFromCart(Me m,String bks){
		String []str=bks.split(",");
		for(String st:str){
			if(st!=null && !st.equals("")){
				try {
					UsrService.DropfromCart(m, Integer.parseInt(st));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 创建订单
	 * @param bname
	 * @param uid
	 * @param bnum
	 * @param balance
	 * @return
	 */
	public static boolean buildOrder(String bid,int uid,int bnum,double balance){
		StringBuffer sql=new StringBuffer();
		java.util.Date dt=new java.util.Date();
		Date date=new Date(dt.getTime());
		Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		sql.append("INSERT INTO orders(u_id,o_booknum,o_booksname,o_orderdate,o_totalprice,o_isdeliver) ");
		sql.append("values("+uid+","+bnum+",'"+bid+"','"+time+"',"+balance+",0)");
		return getModel.SaveOrder(sql.toString());
	}
	
	/**
	 * 更新余额
	 * @param request
	 * @param total
	 */
	public static boolean UpdateBalance(Me m,double total){
		String sql="";
		if(m.getU_balance()<total){
			try {
				throw new Exception("balance is not enough");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			sql="UPDATE userinfo SET u_balance="+(m.getU_balance()-total)+" WHERE u_id="+m.getU_id();
		}
		return UsrService.UpdateMe(sql);
	}
	
	/**
	 * 更新付款单
	 * @param oid
	 * @return
	 */
	public static boolean UpdatePayMent(Integer oid){
		StringBuffer sb=new StringBuffer();
		sb.append("UPDATE payment SET p_state=1,p_date=?,p_code=? WHERE o_id=?");
		java.util.Date date=new java.util.Date();
		Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		String code=java.util.UUID.randomUUID().toString();
		Object[] obj=new Object[3];
		obj[0]=time;
		obj[1]=code;
		obj[2]=oid;
		return getModel.UpdatePayment(sb.toString(),obj);
	}
	
	/**
	 * 获取订单
	 * @param uid
	 * @param flag
	 * @return
	 */
	public static List<Order> getOrder(HttpServletRequest request,Integer uid,String flag){
		StringBuffer sql=new StringBuffer();
		List<Order> list=null;
		sql.append("SELECT ord.o_id AS o_id, ord.u_id AS u_id,ord.o_booknum AS o_booknum, ord.o_booksname AS o_booksname, "
				+ " ord.o_orderdate AS o_orderdate, ord.o_totalprice AS o_totalprice,"
				+ " ord.o_isdeliver AS o_isdeliver, pym.p_state AS p_state "
				+ " FROM orders ord "
				+ " LEFT JOIN payment pym "
				+ " ON ord.o_id=pym.o_id "
				+ " WHERE ord.u_id=?");
		if(flag!=null && flag!="" && "fail".equals(flag)){
			request.setAttribute("flag","fail");
			sql.append(" AND pym.p_state=0");
		}else{
			sql.append(" AND pym.p_state=1");
		}
		list=getModel.getOrder(sql.toString(), uid);
		if(list!=null){
			for(Order ord:list){
				String str=ord.getO_booksname();
				String res=getBooksName(ord, str);
				ord.setO_booksname(res);
			}
		}
		return list;
	}
	
	/**
	 * 获取用户所有订单
	 * @param uid
	 * @return
	 */
	public static List<Order> getOrderByUid(Integer uid){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT o_id,u_id,o_booknum,o_booksname,o_orderdate,o_totalprice,o_isdeliver FROM orders WHERE u_id=? ORDER BY o_id DESC");
		return getModel.getOrder(sql.toString(), uid);
	}
	/**
	 * 根据订单ID获取
	 * @param oid
	 * @return
	 */
	public static Order getOrderByOid(Integer oid){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT o_id,u_id,o_booknum,o_booksname,o_orderdate,o_totalprice,o_isdeliver FROM orders WHERE o_id=?");
		return getModel.getOrderByOid(sql.toString(), oid);
	}
	
	public static List<Comments> getCommentBid(Integer uid){
			return getModel.getCommentsByuid(uid);
	}
	
	/**
	 * 获取订单中所有可以评论的Book
	 * @param oid
	 * @return
	 */
	public static List<Book> getOrderBooks(Integer oid){
		List<Book> list=new ArrayList<>();
		List<Comments> clist=new ArrayList<>(); 
		Order ord=getOrderByOid(oid);
		clist=getCommentBid(ord.getU_id());
		String str=ord.getO_booksname();
		String [] bks=str.split(",");
		List<Integer> blist=new ArrayList<>();
		for(String ss:bks){
			if(ss!=null && !ss.equals("")){
					blist.add(Integer.parseInt(ss));
				}
		}
		outer:
		if(clist!=null && clist.size()>0){
			for(Comments c:clist){
				for(int i=0;i<blist.size();i++){
					if(blist.get(i).intValue()==c.getBid().intValue()){
						blist.remove(i);
						if(blist.size()==0){
							break outer;
						}
					}else{
						continue;
					}
				}
			}
		}
		
		for(Integer i:blist){
			list.add(getModel.getBookById(i));
		}
		return list;
	}
	
	/**
	 * 重写从数据库读出来的o_bookname字段
	 * @param ord
	 * @param str
	 * @return
	 */
	private static  String getBooksName(Order ord,String str){
		String []st=str.split(",");
		String restr="";
		for(String ss:st){
			if(ss!=null && !ss.equals("")){
				Book b=getModel.getBookById(Integer.parseInt(ss));
				restr=restr+b.getB_id()+":"+b.getB_bookname()+",";
			}
		}
		return restr;
	}
	
	public static boolean SaveComment(Integer uid,Integer bid,String Content){
		StringBuffer sb=new StringBuffer();
		sb.append("INSERT INTO `comment`(uid,bid,time,content) VALUES(?,?,?,?)");
		java.util.Date date=new java.util.Date();
		Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st=format.format(date);
		Object [] obj=new Object[4];
		obj[0]=uid;
		obj[1]=bid;
		obj[2]=st;
		obj[3]=Content;
		return getModel.SaveComment(sb.toString(),obj);
		
	}
}
