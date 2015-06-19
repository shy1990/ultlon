package com.sj1688.ultlon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.expr.NewArray;

public class DateUtil {
	/** 时间格式 */  
    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
      
    /** 
     * 对比两个时间相差毫秒数
     *  
     * @param dateA 
     * @param dateB 
     * @return 
     * @throws ParseException 
     */  
    public static long compare(String dateA, String dateB) throws ParseException {  
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);  
        return Math.abs(df.parse(dateA).getTime() - df.parse(dateB).getTime());  
    }  
    
    /**
     * 对比当前时间，返回时间差
     * @param dateA
     * @param dateB
     * @return 时间差（毫秒）
     * @throws ParseException
     */
    public static long compareNowDate(String date) throws ParseException {  
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);  
        long compare = Math.abs(new Date().getTime() - df.parse(date).getTime());
        return compare;  
    }  
    
    
    /**
     * 对比当前时间，返回时间差
     * @param dateA
     * @param dateB
     * @return 时间差（毫秒）
     * @throws ParseException
     */
    public static long compareNowDate(Date date) {
    	long compare = 0;
    	try {
    		 DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);  
    		 compare = Math.abs(new Date().getTime() - date.getTime() );
		} catch (Exception e) {
			System.out.println("时间转换异常："+e.getMessage());
		}
        return compare;  
    } 
    
    /**
     * 字符串转date类型
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str){
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
	    try {
			return sdf.parse(sdf.format(new Date(Long.parseLong(str))));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    return null;
    }
    
    /**
     * 格式化时间戳字符串
     * @param str
     * @return
     */
    public static String dateStrFormat(String str){
    	SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
	    return sdf.format(new Date(Long.parseLong(str))); 
    }
      
 /*   *//** 
     * 主函数 
     *  
     * @param args 
     *//*  
    public static void main(String[] args) {  
           // boolean isExceed = compare("2010-01-05 22:22:21", "2010-01-05 22:22:25"); 
            //System.out.println("两个时间相比, 是否相差超过3秒：" + compareNowDate("2015-06-05 09:57:00"));
        	System.out.println(strToDate("1434608814000"));
            
    }  */
}
