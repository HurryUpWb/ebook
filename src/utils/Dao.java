package utils;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Connector;

public class Dao {
	/**
	 * 增删改的通用方法
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static boolean update(String sql,Object...objects){
		boolean flag=true;
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=Connector.getConnection();
			pre=conn.prepareStatement(sql);
			if(objects!=null && objects.length>0){
				for(int i=0;i<objects.length;i++){
					pre.setObject(i+1, objects[i]);
				} 
			}
			flag=pre.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Connector.ReleaseConnection(conn, pre, null);
		}
		return flag;
	}
	
	/**
	 * 查询的通用方法
	 * @param clazz
	 * @param sql
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E get(Class<E> clazz,String sql,Object...objects){
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet res=null;
		ResultSetMetaData rsmd=null;
		Map<String,Object> map=new HashMap<>();
		
		try {
			//获取连接和PrepareStatement对象
			conn=Connector.getConnection();
			pre=conn.prepareStatement(sql);
			//遍历Object数组，把值赋给带有占位符的sql语句
			if(objects!=null){
				for(int i=0;i<objects.length;i++){
					pre.setObject(i+1,objects[i]);
				}
			}
			
			res=pre.executeQuery();
			rsmd=res.getMetaData();
			//根据ResultSet获取数据库的元数据
			while(res.next()){
			for(int i=0;i<rsmd.getColumnCount();i++){
					//获取数据表中的列名
					String columnname=rsmd.getColumnName(i+1);
					//获取列对应的对象
					Object columnvalue=res.getObject(i+1);
					//放到map中
					map.put(columnname, columnvalue);
				}
			}
			
			for(Map.Entry<String,Object> m:map.entrySet()){
				String name=m.getKey();
				Object value=m.getValue();
				ReflectionUtils.setFieldValue(obj,name , value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Connector.ReleaseConnection(conn, pre, null);
		}
		return (E)obj;
	}
	
	/**
	 *  获取所有List
	 * @param clazz
	 * @param sql
	 * @param objects
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public static <E> List<E> getAll(Class<E> clazz,String sql,Object...objects){
		List<E> list=new ArrayList<>();
		Object obj=null;
		
		Connection connection=null;
		PreparedStatement pre=null;
		ResultSet resultSet=null;
		ResultSetMetaData rsmd=null;
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			connection=Connector.getConnection();
			pre=connection.prepareStatement(sql);
			
			if(objects!=null){
				for(int i=0;i<objects.length;i++){
					pre.setObject(i+1, objects[i]);
				}
			}
			resultSet=pre.executeQuery();
			rsmd=resultSet.getMetaData();
			while(resultSet.next()){
				try {
					obj=clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e ) {
					e.printStackTrace();
				}
				
				for(int i=0;i<rsmd.getColumnCount();i++){
					String columnName=rsmd.getColumnName(i+1);
					Object columnValue=resultSet.getObject(i+1);
					map.put(columnName, columnValue);
				}
				
				for(Map.Entry<String, Object> m:map.entrySet()){
					String key=m.getKey();
					Object value=m.getValue();
					ReflectionUtils.setFieldValue(obj, key, value);
				}
				list.add((E) obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Connector.ReleaseConnection(connection, pre, resultSet);
		}
		return list;
	}
	
	/**
	 * 查找满足条件的数据的总和
	 * SQL语句中 查询的总和 列名必须为 ‘sum’
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static Integer getNumOfTab(String sql,Object ...objects){
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet res=null;
		int num = 0;
		
		try {
			conn=Connector.getConnection();
			stm=conn.prepareStatement(sql);
			
			if(objects!=null && objects.length>0){
				for(int i=0;i<objects.length;i++){
					stm.setObject(i+1,objects[i]);
				}
			}
			res=stm.executeQuery();
			while(res.next()){
				num=res.getInt("sum");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}
	/**
	 * 针对联合查询 用List<Map<?,?>>取出来
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static List<Map<String,Object>> getInfo(String sql,Object ...objects){
		List<Map<String,Object>> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet res=null;
		ResultSetMetaData rsmd=null;
		
		try {
			conn=Connector.getConnection();
			stm=conn.prepareStatement(sql);
			if(objects!=null && objects.length>0){
				for(int i=0;i<objects.length;i++){
					stm.setObject(i+1, objects[i]);
				}
			}
			res=stm.executeQuery();
			rsmd=res.getMetaData();
			while(res.next()){
				Map<String,Object> map=new HashMap<>();
				for(int i=0;i<rsmd.getColumnCount();i++){
					//获取数据表中的列名
					String columnname=rsmd.getColumnName(i+1);
					//获取列对应的对象
					Object columnvalue=res.getObject(i+1);
					//放到map中
					map.put(columnname, columnvalue);
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
