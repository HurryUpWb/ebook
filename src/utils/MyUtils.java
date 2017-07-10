package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MyUtils {
	/**
	 * 复制文件
	 * @param source 源地址
	 * @param destination 目标地址
	 */
	public static void CopyFile(String source,String destination){
		InputStream fileInputStream=null;
		OutputStream fileOutputStream=null;
		try {
			fileInputStream=new FileInputStream(new File(source));
			fileOutputStream=new FileOutputStream(new File(destination));
			byte []b=new byte[1024];
			int len;
			while((len=fileInputStream.read(b))!=-1){
				fileOutputStream.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * 判断是否为空
	 * @param obj
	 * @return
	 */
	public static boolean IsNull(Object obj){
		if(obj instanceof String && obj!=null){
			if(obj!=""){
				return false;
			}
		}else if(obj!=null)
			return false;
		return true;
	}
	
	/**
	 * 转为UTF-8格式字符串
	 * @param str
	 * @return
	 */
	public static String ChangeEncode(String str){
		if(!IsNull(str)){
			try {
				return new String(str.getBytes("utf8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static String getRandomStr(){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i <8; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	}
}
