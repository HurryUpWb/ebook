package service;

import java.util.List;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import beans.Adress;
import beans.Book;
import beans.Cart;
import beans.Me;
import beans.UserCart;
import utils.Dao;
import utils.MyUtils;
import utils.Validator;
import utils.getModel;

public class UsrService {
	/**
	 * 获取用户对象
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public static Me getMe(String uname,String pwd){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT u_id,u_account,u_pwd,u_name,u_balance,u_authority,u_email,u_telephone FROM userinfo");
		if(Validator.isEmail(uname)){
			sql.append(" WHERE u_email=? AND u_pwd=? ");
			
		}else if(Validator.isMobile(uname)){
			sql.append(" WHERE u_telephone=? AND u_pwd=? ");
		}else{
			return null;
		}
		return getModel.getMe(sql.toString(),uname, pwd);
	}
	
	public static Me getMe(Integer uid){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT u_id,u_account,u_pwd,u_name,u_balance,u_authority,u_email,u_telephone FROM userinfo");
		sql.append(" WHERE u_id=?");
		if(uid!=null){
			return getModel.getMe(sql.toString(),uid);
		}else{
			return null;
		}
	}
	/**
	 * 更新用户信息
	 * @param sql
	 */
	public static boolean UpdateMe(String sql){
		return Dao.update(sql, null);
	}
	
	/**
	 * 添加到购物车
	 * @param request
	 * @return
	 */
	public static String AddtoCart(HttpServletRequest request){
		JSONObject jsonObject=new JSONObject();
		try {
			if(request.getSession().getAttribute("me")==null){
				jsonObject.append("msg","notlogin");
			}else{
				String bid=request.getParameter("bid");
				String num=request.getParameter("num");
				String flag=request.getParameter("flag");
				Me m=(Me)request.getSession().getAttribute("me");
				if(!MyUtils.IsNull(bid) && !MyUtils.IsNull(num)){
					Book b=getModel.getBookById(Integer.parseInt(bid));
					m.getCart().AddBook(m,b, Integer.parseInt(num),flag);
					//111
					SaveCart(CartToUserCart(m.getCart(),m.getU_id()));
					jsonObject.append("msg","success");
				}else{
					jsonObject.append("msg","norecord");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	/**
	 * 从购物车中删除
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	public static String DropfromCart(Me me,Integer bid) throws JSONException{
		JSONObject jsonObject=new JSONObject();
//		String bid=request.getParameter("bid");
		Integer i=0;
//		Me me=(Me)request.getSession().getAttribute("me");
		if(!MyUtils.IsNull(bid) && me!=null){
			i=me.getCart().DropBook(getModel.getBookById(bid));
			//1111
			SaveCart(CartToUserCart(me.getCart(),me.getU_id()));
		}
		if(i!=0){
				jsonObject.append("msg","ok");
		}else{
			jsonObject.append("msg","fail");
		}
		return jsonObject.toString();
	}

	
	public static UserCart CartToUserCart(Cart cart,Integer uid){
		String str="";
		for(Entry<Book,Integer> map:cart.getBookMap().entrySet()){
			int bid=map.getKey().getB_id();
			int num=map.getValue();
			str=str+bid+":"+num+",";
		}
		return new UserCart(uid.intValue(),str);
	}
	
	public static Cart UserCartToCart(UserCart userCart){
		Cart cart=null;
		String bks="";
		if(userCart!=null && userCart.getBooks()!="" && userCart.getBooks().length()>0){
			cart=new Cart();
			bks=userCart.getBooks();
			String [] str=bks.split(",");
			for(String st:str){
				if(st!="" && st.length()>0){
					String[] sbtr=st.split(":");
					Book b=getModel.getBookById(Integer.parseInt(sbtr[0]));
					cart.getBookMap().put(b, Integer.parseInt(sbtr[1]));
				}else{
					continue;
				}
			}
		}
		return cart;
	}
	
	/**
	 * 获取用户购物车
	 * @param uid
	 * @return
	 */
	public static UserCart getCart(Integer uid){
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT u_id,books FROM usercart WHERE u_id=?");
		return getModel.getUserCart(sb.toString(), uid);
	}
	
	
	/**
	 * 保存或更新用户购物车
	 * @param cart
	 */
	public static void SaveCart(UserCart cart){
		StringBuffer sb=new StringBuffer();
		int crt=getCart(cart.getU_id()).getU_id();
		if(crt!=0){
			sb.append("UPDATE usercart SET books=? WHERE u_id=?");
		}else{
			sb.append("INSERT INTO usercart(books,u_id) values(?,?)");
		}
		getModel.SaveUserCart(sb.toString(),cart.getBooks(),cart.getU_id());
	}
	
	/**
	 * 获取用户的地址列表
	 * @param bid
	 */
	public static List<Adress> getAdress(Integer bid){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT uid,adress FROM adress WHERE uid=?");
		return getModel.getAllAdress(sql.toString(),bid);
	}
}
