package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库链接获取
 * @author Administrator
 *
 */
public class Connector {
	private static String DriverClass="";
	private static String url="";
	private static String dbname="";
	private static String user="";
	private static String psw="";
	
	private static Properties pro=new Properties();
	
	static{
		InputStream inStream;
		try {
//			inStream = new FileInputStream(new File("DbConfig.properties"));
			inStream=Connector.class.getClassLoader().getResourceAsStream("DbConfig.properties");
			pro.load(inStream);
			DriverClass=pro.getProperty("driverclass");
			url=pro.getProperty("url");
			dbname=pro.getProperty("dbname");
			user=pro.getProperty("user");
			psw=pro.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		String uri=url+"/"+dbname;
		Connection con=null;
		try {
			Class.forName(DriverClass).newInstance();
			con=DriverManager.getConnection(uri, user, psw);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void ReleaseConnection(Connection conn,Statement stm,ResultSet resultSet){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
