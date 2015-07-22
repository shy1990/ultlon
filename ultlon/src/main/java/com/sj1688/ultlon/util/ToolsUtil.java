/**  
* @Title: ToolsUtil.java
* @Package com.sanji.mall.common.util
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-23 下午2:02:11
* @version V1.0  
*/
package com.sj1688.ultlon.util;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;




/**
 * @ClassName: ToolsUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-23 下午2:02:11
 */
public class ToolsUtil {

	private static final String BHT_SYS_FILE_DIR = "yisou_file_dir";
	private static Logger log = Logger.getLogger("ToolsUtil");
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	
	/**
	 * 将string(格式如：a,b,c)转换为List
	 * 
	 * @Title: StringConvertList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return List<String>    返回类型
	 */
	public static List<String> StringConvertList(String str) {
		List<String> list = new ArrayList<String>();
		if (str != null && !"".equals(str)) {
			String[] strs = str.split(",");
			for (int i = 0; i < strs.length; i++) {
				list.add(strs[i].trim());
			}
		}
		return list;
	}

	
}
