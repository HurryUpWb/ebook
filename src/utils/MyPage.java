package utils;

import java.util.List;

import beans.Book;

public class MyPage {
	private int index=1;
	private int totalRecNum;
	private int perPageNum;
	private int totalPageNum;
	private  List<Book> objlist=null;
	/**
	 * 
	 * @param perPageNum 每页条数
	 * @param list	包含记录的list
	 */
	public MyPage(int perPageNum,List<Book> list) {
		super();
		this.perPageNum=perPageNum;
		this.objlist=list;
		this.totalRecNum=list.size();
		if((objlist.size()%perPageNum)>0)
			this.totalPageNum=(objlist.size()/perPageNum)+1;
		else
			this.totalPageNum=objlist.size()/perPageNum;
	}
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPageNum(){
		return totalPageNum;
	}
	/**
	 * 总记录条数
	 * @return
	 */
	public int getTotalRecNum(){
		return objlist.size();
	}
	
	/**
	 * 设置页号
	 * @param index
	 */
	public void setIndex(int index){
		if(index<0){
			this.index=1;
		}else if(index>totalPageNum){
			this.index=totalPageNum;
		}else{
			this.index=index;
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean HasPrve(){
		if(index==1)
			return false;
		else
			return true;
	}
	
	public boolean HasNext(){
		if(index==totalPageNum)
			return false;
		else
			return true;
	}
	
	public List<Book> SwchPage(){
		if(index==totalPageNum)
			return objlist.subList(((index-1)*perPageNum),totalRecNum);
		else
			return objlist.subList((index-1)*perPageNum,(index-1)*perPageNum+perPageNum);
	}
	
}
