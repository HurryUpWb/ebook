package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import beans.Book;
import beans.Comments;
import beans.Type;
import utils.MyUtils;
import utils.MyPage;
import utils.getModel;

public class Putview {
//	private static final String source="D:\\bookshop_pic\\";  //在linux下需要修改
	private static final String source="/home/bookshop_pic/";
	private static Logger log=Logger.getLogger(Putview.class);
	/**
	 * 获取最近加入的书籍
	 * @return
	 */
	public static String getShowLastBooks(HttpServletRequest request){
		List<Book> list=getModel.getLastBook();
		JSONArray arry=new JSONArray();
		int i=1;
		for(Book b:list){
			String str=b.getB_pic_large();
			Map<String,String> map=new HashMap<>();
			map.put("id",b.getB_id().toString());
			map.put("name",b.getB_bookname());
			map.put("pic",str);
			//将值传回前端 
			try {
				arry.put(i, map);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			i++;
		}
		return arry.toString();
	}
	
	/**
	 * 获得所有图书分累
	 * @return
	 */
	public static List<Type> getTypes(){
		return getModel.getAllType();
	}
	/**
	 * 根据ID获取书籍
	 * @param id
	 * @return
	 */
	public static Book getOneBook(Integer id){
		return getModel.getBookById(id);
	}
	
	/**
	 * 根据书籍ID获取所有评论
	 * @param bid
	 * @return
	 */
	public static List<Comments> getComments(Integer bid){
		return getModel.getComments(bid);
	}
	
	public static MyPage getBooks(HttpServletRequest request){
		String bname="";
		String bauthor="";
		String btype="";
		String cate="";
		String price="";
		String pubs="";
		bname=request.getParameter("bname");
		bauthor=request.getParameter("author");
		btype=request.getParameter("btype");
		cate=request.getParameter("cate");
		price=request.getParameter("price");
		pubs=request.getParameter("pubs");
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT b_id,b_booktype,b_pubs,b_bookname,b_author,b_price,b_description,b_pic,b_pic_large,b_show ");
		sb.append(" FROM bookinfo WHERE b_show=1 ");
		if(!MyUtils.IsNull(bname)){
			sb.append("AND b_bookname like '%"+bname+"%' ");
		}
		if(!MyUtils.IsNull(bauthor)){
			sb.append("AND b_author like '%"+bauthor+"%' ");
		}
		if(!MyUtils.IsNull(btype) && !"all".equals(btype)){
			sb.append("AND b_booktype='"+btype+"' ");
		}
		if(!MyUtils.IsNull(cate) && !MyUtils.IsNull(price)){
			double bprice=Double.parseDouble(price);
			sb.append("AND b_price ");
			if("less".equals(cate)){
				sb.append("<"+bprice+" ");
			}
			if("lessequal".equals(cate)){
				sb.append("<="+bprice+" ");
			}
			if("equal".equals(cate)){
				sb.append("="+bprice+"");
			}
			if("moreequal".equals(cate)){
				sb.append(">="+bprice+" ");
			}
			if("more".equals(cate)){
				sb.append(">"+bprice+" ");
			}
		}
		if(!MyUtils.IsNull(pubs)){
			sb.append("AND b_pubs like '%"+pubs+"%' ");
		}
		List<Book> list=getModel.getBookBycate(sb.toString());
		if(list!=null && list.size()>0){
			MyPage p=new MyPage(5,list);
			return p;
		}else{
			return null;
		}
	}
	
	public static List<Book> getBookByType(String typeno){
		String sql="SELECT * FROM bookinfo WHERE b_show=1 AND b_booktype='"+typeno+"'";
		return getModel.getBookBycate(sql);
	}
}
