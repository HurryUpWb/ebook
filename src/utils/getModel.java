package utils;

import java.util.List;

import org.apache.log4j.Logger;

import beans.Adress;
import beans.Book;
import beans.Comments;
import beans.Me;
import beans.Order;
import beans.Type;
import beans.UserCart;

public class getModel {
	/**
	 * 根据ID获取Book
	 * @param id
	 * @return
	 */
	public static Book getBookById(Integer id){
		StringBuffer sb=new StringBuffer();
		sb.append("select b_id,b_booktype,b_pubs,b_bookname,b_author,b_price,b_description,b_pic,b_pic_large,b_show");
		sb.append(" from bookinfo where b_id=?");
		return Dao.get(Book.class,sb.toString(),id.intValue());
		
	}
	
	/**
	 * 选取最后几本展示
	 * @return
	 */
	public static List<Book> getLastBook(){
		StringBuffer sb=new StringBuffer();
		sb.append("select b_id,b_booktype,b_pubs,b_bookname,b_author,b_price,b_description,b_pic,b_pic_large,b_show");
		sb.append(" from bookinfo where b_show=1 group by b_id desc limit 0,6");
		return Dao.getAll(Book.class, sb.toString(),null);
	}
	/**
	 * 查找所有图书种类
	 * @return
	 */
	public static List<Type> getAllType(){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT TYPENO,TYPEINFO FROM TYPE");
		return Dao.getAll(Type.class,sb.toString(),null);
	}
	/**
	 * 根据书籍ID获取所有评论
	 * @param bid 书籍ID
	 * @return
	 */
	public static List<Comments> getComments(Integer bid){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT cid,c.uid,bid,u.u_name,time,content "
				+ " FROM `comment` c "
				+ " LEFT JOIN `userinfo` u "
				+ " ON c.uid=u.u_id "
				+ " WHERE bid=? AND c.isshow=1");
		return Dao.getAll(Comments.class,sb.toString(),bid.intValue());
	}
	
	/**
	 * 根据uid获取Comment
	 * @param uid
	 * @return
	 */
	public static List<Comments> getCommentsByuid(Integer uid){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT cid,uid,bid,time,content  FROM `comment` WHERE uid=?");
		return Dao.getAll(Comments.class,sb.toString(),uid.intValue());
	}
	
	/**
	 * 按条件获取书籍
	 * @param sql
	 * @return
	 */
	public static List<Book> getBookBycate(String sql){
		return Dao.getAll(Book.class, sql,null);
	}
	
	/**
	 * 获取用户信息
	 * @param sql
	 * @return
	 */
	public static Me getMe(String sql,String uname,String pwd){
		return Dao.get(Me.class, sql,uname,pwd);
	}
	
	public static Me getMe(String sql,Integer id){
		return Dao.get(Me.class, sql,id);
	}
	
	/**
	 * 保存或更新用户信息
	 * @param sql
	 * @param account
	 * @param pwd
	 * @param name
	 * @param email
	 * @param phone
	 * @return
	 */
	public static boolean SaveMe(String sql,String account,String pwd,String name,String email,String phone){
		return Dao.update(sql, account,pwd,name,email,phone);
	}
	
	/**
	 * 验证邮箱是否被注册
	 * @param sql
	 * @param email
	 * @return
	 */
	public static Me getMeByEmail(String sql,String email){
		return Dao.get(Me.class, sql, email);
	}
	
	/**
	 * 保存用户购物车
	 * @param sql
	 */
	public static void SaveUserCart(String sql,String books,Integer uid){
			Dao.update(sql,books,uid);
	}
	
	/**
	 * 获取用购物车
	 * @param sql
	 * @param uid
	 * @return
	 */
	public static UserCart getUserCart(String sql,Integer uid){
		return Dao.get(UserCart.class, sql,uid);
	}
	
	/**
	 * 获取用户的地址集
	 * @param sql
	 * @param uid
	 * @return
	 */
	public static List<Adress> getAllAdress(String sql,Integer uid){
		return Dao.getAll(Adress.class, sql,uid);
	}
	
	/**
	 * 提交就保存订单
	 * @param sql
	 */
	public static boolean SaveOrder(String sql){
		return Dao.update(sql,null);
	}
	
	/**
	 * 根据用户ID获取订单
	 * @param sql
	 * @param uid
	 * @return
	 */
	public static List<Order> getOrder(String sql,Integer uid){
		return Dao.getAll(Order.class, sql, uid);
	}
	/**
	 * 根据订单Id获取订单
	 * @param sql
	 * @param oid
	 * @return
	 */
	public static Order getOrderByOid(String sql,Integer oid){
		return Dao.get(Order.class, sql,oid);
	}
	
	public static boolean UpdatePayment(String sql,Object...objects){
		return Dao.update(sql, objects);
	}
	
	public static boolean SaveComment(String sql,Object...objects){
		return Dao.update(sql, objects);
	}
	
}
