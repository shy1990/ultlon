/**  
* @Title: ToolsUtil.java
* @Package com.sanji.mall.common.util
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-23 下午2:02:11
* @version V1.0  
*/
package com.sj1688.ultlon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
	 * 检查是否为null
	* @Title: isNull
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param obj
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author ZhouZhangbao
	 */
	public static boolean isNull(Object obj) {
		if (obj == null)
			return true;
		return false;
	}

	/**
	 * 检查字符串是否为空
	* @Title: isEmpty
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param str
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author ZhouZhangbao
	 */
	public static boolean isEmpty(String str) {
		if ("".equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * 检查字符串是否为空或null
	* @Title: isNullOrEmpty
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param str
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author ZhouZhangbao
	 */
	public static boolean isNullOrEmpty(String str) {
		if (isNull(str) || isEmpty(str))
			return true;
		return false;
	}

	/**
	 * 功能说明：string转换编码格式
	* @Title: StringEncode
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param str
	* @param @param code 编码格式,比如(utf-8,GBK等)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String StringEncode(String str, String code) {
		if (isNullOrEmpty(str))
			return null;
		if (isNullOrEmpty(code))
			return null;
		try {
			return new String(str.getBytes(), code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串中某个字符出现多少次
	* @Title: contain
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param str
	* @param @param ch
	* @param @return    设定文件
	* @return Integer    返回类型
	* @author ZhouZhangbao
	 */
	public static Integer contain(String str, char ch) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (ch == str.charAt(i))
				count++;
		}
		return count;
	}

	/**
	 * 功能说明：获得web根路径
	* @Title: getResourcePath
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 *//*
	public static String getResourcePath() {
		String resPath = ServletActionContext.getServletContext().getRealPath(
				"");
		// System.out.println(ss);
		// String resPath = System.getProperty(WEB_APP_ROOT_KEY);
		log.info("path of webAppRootKey is ----> " + resPath);
		return resPath;
	}*/


	/**
	 * 功能说明：将日期格式为年月日
	* @Title: formatDateToYMD
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param date
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String formatDateToYMD(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 功能说明：将日期格式为年月日时分秒
	* @Title: formatDateToYMDHMS
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param date
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String formatDateToYMDHMS(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}


	/**
	 * 随机生成字符，含大写、小写、数字
	* @Title: getRandomChar
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String getRandomChar() {
		int index = (int) Math.round(Math.random() * 2);
		String randChar = "";
		switch (index) {
		case 0:// 大写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 65));
			break;
		case 1:// 小写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 97));
			break;
		default:// 数字
			randChar = String.valueOf(Math.round(Math.random() * 9));
			break;
		}
		return randChar;
	}
	

	

	
	/**
	 * 传入数字金额字符串，返回数字金额对应的中文大字与读法
	* @Title: getCHSNumber
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param money 金额字符串
	* @param @return    金额中文大写
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String getCHSNumber(String money) {
		final String[] CH = { "", "", "拾", "佰", "仟", "万", "", "", "", "亿", "",
				"", "", "兆" };
		final String[] CHS_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒",
				"捌", "玖" };
		String chs = "";
		String tmp_int = money.substring(0, money.indexOf("."));
		String tmp_down = money.substring(money.indexOf(".") + 1);
		char[] tmp_int_char = tmp_int.toCharArray();
		String[] tmp_chs = new String[tmp_int_char.length];
		int tab = 0;
		for (int i = 0; i < tmp_int_char.length; i++) {
			tab = tmp_int_char.length - i - 1;
			if (tmp_int_char.length <= 5) {
				tmp_chs[tab] = CHS_NUMBER[(int) Float
						.parseFloat(tmp_int_char[i] + ".0")];
				if (!tmp_chs[tab].equals("零")) {
					// tmp_int_char.Length - i 为数字所在的位数
					chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
				} else {
					// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
					if (!chs.endsWith("零") && tab != 0) {
						chs = chs + tmp_chs[tab];
					} else if (chs.endsWith("零") && tab == 0) {
						chs = chs.substring(0, chs.length() - 1);
					}
				}
			}
			// 　如果数字的位数大于５和小于９时
			if (tmp_int_char.length > 5 && tmp_int_char.length < 9) {
				tmp_chs[tab] = CHS_NUMBER[(int) Float
						.parseFloat(tmp_int_char[i] + ".0")];
				// 如：123,1234分成两部分
				// 第１部分123：万以上亿以下
				if (tab >= 4) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 3];
						// 　当第１部分算完时在加上"万"
						if (tab == 4) {
							chs = chs + "万";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 4) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								chs = chs + "万";
							} else {
								// 　如果没有零就直接加上"万"
								chs = chs + "万";
							}
						}
					}
				}
				// 如：123,1234分成两部分
				// 第１部分1234：万以下
				if (tab < 4) {
					if (!tmp_chs[tab].equals("零")) {
						// tmp_int_char.Length - i 为数字所在的位数
						chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
					} else {
						// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
						if (!chs.endsWith("零") && tab != 0) {
							chs = chs + tmp_chs[tab];
						}
						if (chs.endsWith("零") && tab == 0) {
							chs = chs.substring(0, chs.length() - 1);
						}
					}
				}
			}
			// 　如果数字的位数大于５和小于９时
			if (tmp_int_char.length >= 9 && tmp_int_char.length <= 12) {
				tmp_chs[tab] = CHS_NUMBER[(int) Float
						.parseFloat(tmp_int_char[i] + ".0")];
				if (tab >= 8 && tab < 12) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 7];
						// 　当第１部分算完时在加上"万"
						if (tab == 8) {
							chs = chs + "亿";

						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 8) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								chs = chs + "亿";
							} else {
								// 　如果没有零就直接加上"万"
								chs = chs + "亿";
							}
						}
					}
				}
				// 如：123,1234分成两部分
				// 第１部分123：万以上亿以下
				if (tab >= 4 && tab < 8) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 3];
						// 　当第１部分算完时在加上"万"
						if (tab == 4) {
							chs = chs + "万";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 4) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								if (!chs.endsWith("亿"))
									chs = chs + "万";
							} else {
								// 　如果没有零就直接加上"万"
								if (!chs.endsWith("亿"))
									chs = chs + "万";
							}
						}
					}
				}
				// 如：123,1234分成两部分
				// 第１部分1234：万以下
				if (tab < 4) {
					if (!tmp_chs[tab].equals("零")) {
						// tmp_int_char.length - i 为数字所在的位数
						chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
					} else {
						// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
						if (!chs.endsWith("零") && tab != 0) {
							chs = chs + tmp_chs[tab];
						}
						if (chs.endsWith("零") && tab == 0) {
							chs = chs.substring(0, chs.length() - 1);
						}
					}
				}
			}
			// 　如果数字的位数大于12和小于16时
			if (tmp_int_char.length > 12 && tmp_int_char.length <= 16) {
				tmp_chs[tab] = CHS_NUMBER[(int) Float
						.parseFloat(tmp_int_char[i] + ".0")];
				if (tab >= 12 && tab < 16) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 11];
						// 　当第１部分算完时在加上"万"
						if (tab == 12) {
							chs = chs + "兆";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 12) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								chs = chs + "兆";
							} else {
								// 　如果没有零就直接加上"万"
								chs = chs + "兆";
							}
						}
					}
				}
				if (tab >= 8 && tab < 12) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 7];
						// 　当第１部分算完时在加上"万"
						if (tab == 8) {
							chs = chs + "亿";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 8) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								if (!chs.endsWith("兆"))
									chs = chs + "亿";
							} else {
								// 　如果没有零就直接加上"万"
								if (!chs.endsWith("兆"))
									chs = chs + "亿";
							}
						}
					}
				}
				// 如：123,1234分成两部分
				// 第１部分123：万以上亿以下
				if (tab >= 4 && tab < 8) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 3];
						// 　当第１部分算完时在加上"万"
						if (tab == 4) {
							chs = chs + "万";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 4) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								if (!chs.endsWith("亿"))
									if (!chs.endsWith("兆"))
										if (!chs.endsWith("兆"))
											chs = chs + "万";
							} else {
								// 　如果没有零就直接加上"万"
								if (!chs.endsWith("亿"))
									if (!chs.endsWith("兆"))
										chs = chs + "万";
							}
						}

					}
				}
				// 如：123,1234分成两部分
				// 第１部分1234：万以下
				if (tab < 4) {
					if (!tmp_chs[tab].equals("零")) {
						// tmp_int_char.length - i 为数字所在的位数
						chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
					} else {
						// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
						if (!chs.endsWith("零") && tab != 0) {
							chs = chs + tmp_chs[tab];
						}

						if (chs.endsWith("零") && tab == 0) {
							chs = chs.substring(0, chs.length() - 1);
						}
					}
				}
			}
			// 　如果数字的位数大于16
			if (tmp_int_char.length > 16) {
				tmp_chs[tab] = CHS_NUMBER[(int) Float
						.parseFloat(tmp_int_char[i] + ".0")];
				if (tab >= 12) {
					chs = chs + tmp_chs[tab];
					// 　当第１部分算完时在加上"万"
					if (tab == 12) {
						chs = chs + "兆";
					}
				}
				if (tab >= 8 && tab < 12) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 7];
						// 　当第１部分算完时在加上"万"
						if (tab == 8) {
							chs = chs + "亿";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}
						// 当第１部分算完时
						if (tab == 8) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								if (!chs.endsWith("兆"))
									chs = chs + "亿";
							} else {
								// 　如果没有零就直接加上"万"
								if (!chs.endsWith("兆"))
									chs = chs + "亿";
							}
						}
					}
				}
				// 如：123,1234分成两部分
				// 第１部分123：万以上亿以下
				if (tab >= 4 && tab < 8) {
					// 当前数字不是大小零时
					if (!tmp_chs[tab].equals("零")) {
						chs = chs + tmp_chs[tab] + CH[tab - 3];
						// 　当第１部分算完时在加上"万"
						if (tab == 4) {
							chs = chs + "万";
						}
					} else {
						// 当前数字为大小"零"时
						// 判断前一次形成在字符串结尾有没有零
						// 　如果没有零就加上零
						if (!chs.endsWith("零")) {
							chs = chs + tmp_chs[tab];
						}

						// 当第１部分算完时
						if (tab == 4) {
							// 　先判断字符串有没有零
							// 　如果有零时就把零去掉再加上"万"
							if (chs.endsWith("零")) {
								chs = chs.substring(0, chs.length() - 1);
								if (!chs.endsWith("兆"))
									if (!chs.endsWith("亿"))
										chs = chs + "万";
							} else {
								// 　如果没有零就直接加上"万"
								if (!chs.endsWith("兆"))
									if (!chs.endsWith("亿"))
										chs = chs + "万";
							}
						}
					}
				}

				// 如：123,1234分成两部分
				// 第１部分1234：万以下
				if (tab < 4) {
					if (!tmp_chs[tab].equals("零")) {
						// tmp_int_char.length - i 为数字所在的位数
						chs = chs + tmp_chs[tab] + CH[tmp_int_char.length - i];
					} else {// 当数字中有零时就在后加上零，如果超过１个以上的零也只加一个零
						if (!chs.endsWith("零") && tab != 0) {
							chs = chs + tmp_chs[tab];
						}
						if (chs.endsWith("零") && tab == 0) {
							chs = chs.substring(0, chs.length() - 1);
						}
					}
				}
			}
		}

		if (tmp_down != null) {
			char[] tmp = tmp_down.toCharArray();
			if (tmp.length == 1) {
				if (tmp[0] != '0')
					chs = chs + "元"
							+ CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")]
							+ "角整";
				else
					chs = chs + "元整";
			} else {
				if (tmp[1] != '0' && tmp[0] != '0') {
					chs = chs + "元"
							+ CHS_NUMBER[(int) Float.parseFloat(tmp[0] + ".0")]
							+ "角"
							+ CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")]
							+ "分";
				} else if (tmp[1] != '0' && tmp[0] == '0') {
					chs = chs + "元零"
							+ CHS_NUMBER[(int) Float.parseFloat(tmp[1] + ".0")]
							+ "分";
				}
			}
		} else {
			chs = chs + "元整";
		}
		return chs;
	}

	/**
	 * 字符串改成utf-8编码
	* @Title: toUtf8String
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param s
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	

	/**
	 * 删除字符串前面的0000
	* @Title: getRightStr
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param sNum
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String getRightStr(String sNum) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String resultStr = decimalFormat.format(new Double(sNum));
		if (resultStr.matches("^[-+]?\\d+\\.[0]+$")) {
			resultStr = resultStr.substring(0, resultStr.indexOf("."));
		}
		return resultStr;
	}

	/**
	 * 获取上月第一天(String)
	 * 
	 * @return
	 */
	public static String lastMonFirstDay() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String months = "";
		String days = "";
		if (month > 1) {
			month--;
		} else {
			year--;
			month = 12;
		}
		if (!(String.valueOf(month).length() > 1)) {
			months = "0" + month;
		} else {
			months = String.valueOf(month);
		}
		if (!(String.valueOf(day).length() > 1)) {
			days = "0" + day;
		} else {
			days = String.valueOf(day);
		}
		String firstDay = "" + year + "-" + months + "-01";
		String[] lastMonth = new String[2];
		lastMonth[0] = firstDay;
		return firstDay + " 00:00:00";
	}

	/**
	 * 获取上月第一天(Date)
	 * 
	 * @return
	 */
	public static Date lastMonFirstDay_Date() {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(lastMonFirstDay());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 获取上月最后一天
	 * 
	 * @return
	 */
	public static String lastMonLastDay() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String months = "";
		String days = "";
		if (month > 1) {
			month--;
		} else {
			year--;
			month = 12;
		}
		if (!(String.valueOf(month).length() > 1)) {
			months = "0" + month;
		} else {
			months = String.valueOf(month);
		}
		if (!(String.valueOf(day).length() > 1)) {
			days = "0" + day;
		} else {
			days = String.valueOf(day);
		}
		String lastDay = "" + year + "-" + months + "-" + days;
		String[] lastMonth = new String[2];
		lastMonth[1] = lastDay;
		return lastDay + " 23:59:59";
	}

	public static Date lastMonLastDay_Date() {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(lastMonLastDay());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 判断日期格式:yyyy-mm-dd
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate_yyyy_mm_dd(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断时间time是不是在pre跟last之间的时间
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare_date(String time, String pre, String last)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		Boolean flag = false;
		Date _time = f.parse(time);
		Date _pre = f.parse(pre);
		Date _last = f.parse(last);
		if (_time.after(_pre) && _time.before(_last))
			flag = true;
		return flag;
	}

	public static int dayForWeek(Date pTime) throws RuntimeException {
		Calendar c = Calendar.getInstance();
		c.setTime(pTime);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 返回当月有多少天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfTheMonth(Date date) {// 获取当月天数
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date); // 要计算你想要的月份，改变这里即可
		int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);

		return days;
	}

	/**
	 * 返回当月有几个星期天
	 * 
	 * @param date
	 * @return
	 */
	public static int getSundays(Date dat) {
		int sundays = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar setDate = Calendar.getInstance();
		// 从第一天开始
		int day;
		for (day = 1; day <= getDaysOfTheMonth(dat); day++) {
			setDate.set(Calendar.DATE, day);
			String str = sdf.format(setDate.getTime());
			// System.out.println("str:"+str);
			if (str.equals("星期日")) {
				sundays++;
			}
		}
		return sundays;
	}

	public static String getPageSql(String sql, int beginRow, int pageSize) {
		String select = "select";
		String from = "from";
		if (ToolsUtil.isEmpty(sql))
			throw new RuntimeException("sql 语句为空");
		sql = sql.trim();
		String[] temps = sql.split(" ");
		if (temps == null || temps.length == 0) {
			throw new RuntimeException("sql 语句错误" + sql);
		}
		if (temps[0].toUpperCase().equals(select.toUpperCase())) {
			sql = sql.substring(6).trim();
			if (!sql.startsWith("distinct")) {
				sql = select + " rownum trow, " + sql;
				sql = "(" + sql + ") coTable";
				sql = "select coTable.* " + from + sql + " where trow>"
						+ beginRow + " and trow<=" + (beginRow + pageSize); // 注意rownum
																			// 从1
																			// 开始
			} else {
				String temp = "(" + select + " " + sql + ") coT";
				sql = select + " rownum trow,coT.*  " + from + temp;
				sql = "(" + sql + ") coTable";
				sql = "select coTable.* " + from + sql + " where trow>"
						+ beginRow + " and trow<=" + (beginRow + pageSize); // 注意rownum
																			// 从1
																			// 开始
			}

		}
		log.info("getPageSql sql is " + sql);
		return sql;
	}

	/**
	 * java获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

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

	public static Long getTotalPage(Long totalCount, Long rows) {
		Long page = 0L;
		if (totalCount == 0) {
			page = 0L;
		} else {
			if (totalCount <= rows)
				page = 1L;
			if (totalCount > rows && totalCount % rows == 0)
				page = totalCount / rows;
			if (totalCount > rows && totalCount % rows != 0)
				page = totalCount / rows + 1;
		}
		return page;
	}

	/**
	 * 域名根据等级拆分<br>
	 * 例如：ql.zpxy.qlqpw.com,返回map中group值为3,key=1时的值：qlqpw,key=2时的值zpxy,key=3
	 * 时的值ql<br>
	 * 如果头部含有www. 则认为该域名为一级域名<br>
	 * 例如www.xxx.qlqpw.com,返回map中group值为1，key=1时的值：www.xxx.qlqpw.com,不存在key=2
	 * 
	 * @Title: gradeDomain
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param domain
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 周张豹
	 */
	public static Map<String, Object> splitDomain(String domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (domain.startsWith("www.")) {
			map.put("group", 1);
			map.put("1", domain);
			return map;
		}
		;
		String[] domainGroup = domain.split("\\.");
		Integer group = domainGroup.length;
		map.put("group", group - 1);
		for (int i = 0; i < group; i++) {
			map.put(group - i - 1 + "", domainGroup[i]);

		}
		return map;
	}


	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}	

	/**
	 * @Title: getRandomNumber
	 * @Description: TODO(随机生成N位数)
	 * @param @param lenght 随机字符串长度
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	public static String getRandomNumber(int lenght) {
		String[] randomValues = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}
		return str.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(ToolsUtil.getUUID());
	}
	
}
