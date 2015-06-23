/**  
* @Title: JSONUtil.java
* @Package com.sanji.mall.common.util
* @Description: TODO(鐢ㄤ竴鍙ヨ瘽鎻忚堪璇ユ枃浠跺仛锟�?锟斤拷)
* @author ZhouZhangbao  
* @date 2014-7-23 涓嬪崍1:56:54
* @version V1.0  
*/
package com.sj1688.ultlon.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * JSON宸ュ叿锟�?
 * @ClassName: JSONUtil
 * @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜绫荤殑浣滅敤)
 * @author ZhouZhangbao
 * @date 2014-7-23 涓嬪崍1:56:54
 */
public class JsonUtil {
	
	/**
	 * 浠庝竴涓狫SON 瀵硅薄瀛楃鏍煎紡涓緱鍒颁竴涓猨ava瀵硅薄
	* @Title: getObject4JsonString
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @param pojoCalss
	* @param @return    璁惧畾鏂囦欢
	* @return Object    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		if(ToolsUtil.isNullOrEmpty(jsonString))return null;
		Object pojo;
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		pojo = JSONObject.toJavaObject(jsonObject, pojoCalss);
		return pojo;
	}

	/**
	 * 浠庝竴涓狫SON 瀵硅薄瀛楃鏍煎紡涓緱鍒颁竴涓狶ist瀵硅薄
	* @Title: getJsonStr2List
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @param pojoCalss
	* @param @return    璁惧畾鏂囦欢
	* @return List    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	@SuppressWarnings("rawtypes")
	public static List getJsonStr2List(String jsonString, Class pojoCalss) {
		List result = new ArrayList();
		Object[] dtoArray = JsonUtil.getObjectArray4Json(jsonString);
		//warehouseService.delWarehouseMaterial(warehosueId[0]);// 娣诲姞鍓嶅厛鍒犻櫎鍘熸湁鏁版嵁
		if (dtoArray != null && dtoArray.length > 0) {
			for (int i = 0; i < dtoArray.length; i++) {
				try {
					Class c = Class.forName(pojoCalss.getName());
					Object o = c.newInstance();
					o = JsonUtil.getObject4JsonString(dtoArray[i].toString(),pojoCalss);
					result.add(o);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}

		return result;
	}

	/**
	 * 
	 * <p>Title: getList2JsonStr</p>
	 * <p>Description: </p>
	 * @param data
	 * @param pojoCalss
	 * @return
	 */
	public static String getList2JsonStr(List data, Class pojoCalss) {
		StringBuffer result = new StringBuffer();
		result.append("[");
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				try {
					result.append("{");

					Class c = Class.forName(pojoCalss.getName());
					Object o = c.newInstance();
					o = data.get(i);
					Field[] fields = c.getDeclaredFields();

					Method[] methods = c.getDeclaredMethods();
					if (fields != null && fields.length > 0) {

						for (int j = 0; j < fields.length; j++) {
							result.append(fields[j].getName() + ":");
							for (int t = 0; t < methods.length; t++) {
								String temp = "get"
										+ fields[j].getName().toUpperCase()
												.substring(0, 1)
										+ fields[j].getName().substring(1);
								System.out.println(" ####################"
										+ temp);
								if (methods[t].getName().equals(temp)) {
									result.append("'");
									Object invResult = methods[t].invoke(o,
											null);
									if (null != invResult) {
										result.append(invResult.toString());
									}
									result.append("'");
									if (j < fields.length - 1)
										result.append(",");
								}
							}
						}

					}

					result.append("} ");

					if (i < data.size() - 1)
						result.append(",");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}

		result.append("] ");
		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * 
	 * <p>Title: getList2JsonStr</p>
	 * <p>Description: </p>
	 * @param data
	 * @param pojoCalss
	 * @param idField
	 * @param nameField
	 * @return
	 */
	public static String getList2JsonStr(List data, Class pojoCalss,
			String idField, String nameField) {
		StringBuffer result = new StringBuffer();
		result.append("[");
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				try {
					result.append("[");

					Class c = Class.forName(pojoCalss.getName());
					Object o = c.newInstance();
					o = data.get(i);
					Method[] methods = c.getDeclaredMethods();

					String idMethod = "get"
							+ idField.toUpperCase().substring(0, 1)
							+ idField.substring(1);

					String nameMethod = "get"
							+ nameField.toUpperCase().substring(0, 1)
							+ nameField.substring(1);

					String idValue = "";
					String nameValue = "";

					for (int t = 0; t < methods.length; t++) {
						if (methods[t].getName().equals(idMethod)) {
							Object invResult = methods[t].invoke(o, null);
							if (null != invResult) {
								idValue = invResult.toString();
							}
						}
						if (methods[t].getName().equals(nameMethod)) {
							Object invResult = methods[t].invoke(o, null);
							if (null != invResult) {
								nameValue = "'" + invResult.toString() + "'";
							}
						}
					}

					result.append(idValue);
					result.append(",");
					result.append(nameValue);
					result.append("]");

					if (i < data.size() - 1)
						result.append(",");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}

		result.append("] ");
		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * 浠巎son HASH琛ㄨ揪寮忎腑鑾峰彇锟�?锟斤拷map锛屾敼map鏀寔宓屽鍔熻兘
	* @Title: getMap4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return Map    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Map getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Set<String> keys = jsonObject.keySet();
		String key;
		Object value;
		Map valueMap = new HashMap();
		for (String e : keys) {
			key=e;
			value=jsonObject.get(e);
			valueMap.put(key, value);
		}
		return valueMap;
	}

	/**
	 * 浠巎son鏁扮粍涓緱鍒扮浉搴攋ava鏁扮粍
	* @Title: getObjectArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return Object[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		return jsonArray.toArray();

	}

	/** 
	 * 浠巎son瀵硅薄闆嗗悎琛ㄨ揪寮忎腑寰楀埌锟�?锟斤拷java瀵硅薄锟�?
	* @Title: getList4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @param pojoClass
	* @param @return    璁惧畾鏂囦欢
	* @return List    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static List getList4Json(String jsonString, Class pojoClass) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		JSONObject jsonObject;
		Object pojoValue;

		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {

			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toJavaObject(jsonObject, pojoClass);
			list.add(pojoValue);

		}
		return list;

	}

	/**
	 * 浠巎son鏁扮粍涓В鏋愬嚭java瀛楃涓叉暟锟�?
	* @Title: getStringArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return String[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static String[] getStringArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);

		}

		return stringArray;
	}

	/**
	 * 浠巎son鏁扮粍涓В鏋愬嚭javaLong鍨嬪璞℃暟锟�?
	* @Title: getLongArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return Long[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Long[] getLongArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			longArray[i] = jsonArray.getLong(i);

		}
		return longArray;
	}

	/**
	 * 浠巎son鏁扮粍涓В鏋愬嚭java Integer鍨嬪璞℃暟锟�?
	* @Title: getIntegerArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return Integer[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Integer[] getIntegerArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			integerArray[i] = jsonArray.getInteger(i);

		}
		return integerArray;
	}

	/**
	 * 浠巎son鏁扮粍涓В鏋愬嚭java Date 鍨嬪璞℃暟缁勶紝浣跨敤鏈柟娉曞繀椤讳繚锟�?
	* @Title: getDateArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @param DataFormat
	* @param @return    璁惧畾鏂囦欢
	* @return Date[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Date[] getDateArray4Json(String jsonString, String DataFormat) {

		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		String dateString;
		Date date = null;

		for (int i = 0; i < jsonArray.size(); i++) {
			dateString = jsonArray.getString(i);
			//  date = DateUtil.stringToDate(dateString, DataFormat);
			dateArray[i] = date;

		}
		return dateArray;
	}

	/**
	 * 浠巎son鏁扮粍涓В鏋愬嚭java Integer鍨嬪璞℃暟锟�?
	* @Title: getDoubleArray4Json
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param jsonString
	* @param @return    璁惧畾鏂囦欢
	* @return Double[]    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static Double[] getDoubleArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			doubleArray[i] = jsonArray.getDouble(i);

		}
		return doubleArray;
	}

	/**
	 * 灏唈ava瀵硅薄杞崲鎴恓son瀛楃锟�?
	* @Title: getJsonString4JavaPOJO
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param javaObj
	* @param @return    璁惧畾鏂囦欢
	* @return String    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {
		String json =JSONObject.toJSONString(javaObj);
		return json;

	}


	/**
	 * 鏍规嵁琛ㄥ垪灏嗗瓧绗︿覆鏁扮粍鏍煎紡鍖栦负json瀵硅薄
	* @Title: formatBytableCols
	* @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔锟�?
	* @param @param obj
	* @param @param tableCols
	* @param @return    璁惧畾鏂囦欢
	* @return String    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static String formatBytableCols(Object[] obj, String[] tableCols) {
		StringBuffer result = new StringBuffer();
		result.append("{");
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				try {
					result.append(tableCols[i]);
					result.append(":'");
					if (obj[i] instanceof String) {
						String s = null;
						if (obj[i].toString() != null) {
							s = obj[i].toString().replace("\"", "\\\"")
									.replace("'", "\\'").replace("\n", "<br/>")
									.replace("\b", "\\b").replace("\f", "\\f")
									.replace("\r", "").replace("\t", "");
						}
						result.append(s);
					} else if (obj[i] instanceof Integer) {
						result.append((Integer) obj[i]);
					} else if (obj[i] instanceof Long) {
						result.append((Long) obj[i]);
					} else if (obj[i] instanceof Double) {
						result.append((Double) obj[i]);
					} else if (obj[i] instanceof java.sql.Date) {
						result.append((java.sql.Date) obj[i]);
					} else if (obj[i] instanceof Float) {
						result.append((Float) obj[i]);
					}
					result.append("'");
					if (i < obj.length - 1) {
						result.append(",");
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}

			}
		}
		result.append("}");
		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * 鏍规嵁琛ㄥ垪灏嗛泦鍚堟牸寮忓寲涓簀son瀵硅薄
	* @Title: formatBytableCols
	* @Description: TODO(鏍规嵁琛ㄥ垪灏嗛泦鍚堟牸寮忓寲涓簀son瀵硅薄)
	* @param @param list
	* @param @param tableCols
	* @param @return    璁惧畾鏂囦欢
	* @return String    杩斿洖绫诲瀷
	* @author ZhouZhangbao
	 */
	public static String formatBytableCols(List<Object[]> list,String[] tableCols) {
		StringBuffer result = new StringBuffer();
		result.append("[");
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				result.append(formatBytableCols(obj, tableCols));
				if (i < list.size() - 1)
					result.append(",");
			}

		}
		result.append("]");
		return result.toString();
	}

}
