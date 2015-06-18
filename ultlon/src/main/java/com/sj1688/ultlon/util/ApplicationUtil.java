package com.sj1688.ultlon.util;

import java.util.ResourceBundle;

/**
 *功能描述：获得spring bean
 *创建时间：Mar 02, 2010 
 *@version 1.0
 */

public class ApplicationUtil {
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("application");
	
	/**
	 * 获取开箱损服务时间范围
	 * @return
	 */
	public static long getKxs(){
		return convert(bundle.getString("kxs"));
	}
	
	/**
	 * 获取退换货服务时间
	 * @return
	 */
	public static long getThh30(){
		return convert(bundle.getString("thh30"));
	}
	
	/**
	 * 获取维修时间
	 * @return
	 */
	public static long getWx(){
		return convert(bundle.getString("wx"));
	}
	
	/**
	 * 获取多美达换新时间
	 * @return
	 */
	public static long getDmdhx100(){
		return convert(bundle.getString("dmdhx100"));
	}
	
	/**
	 * 把指定时间转换成毫秒
	 * @param str
	 * @return
	 */
	public static long convert(String str){
		String suffix = str.substring(str.length()-1, str.length());
		String val = str.substring(0,str.length()-1);
		if(suffix.equals("h")){
			return (Long.parseLong(val))*60*60*1000; 
		}else if (suffix.equals("d")) {
			return (Long.parseLong(val))*24*60*60*1000; 
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(getKxs());
	}
	
}
