/**  
* @Title: JSONUtil.java
* @Package com.sanji.mall.common.util
* @Description: TODO(用一句话描述该文件做�?��)
* @author ZhouZhangbao  
* @date 2014-7-23 下午1:56:54
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
 * JSON工具�?
 * @ClassName: JSONUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-23 下午1:56:54
 */
public class JsonUtil {
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	* @Title: getObject4JsonString
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @param pojoCalss
	* @param @return    设定文件
	* @return Object    返回类型
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
	 * 从一个JSON 对象字符格式中得到一个List对象
	* @Title: getJsonStr2List
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @param pojoCalss
	* @param @return    设定文件
	* @return List    返回类型
	* @author ZhouZhangbao
	 */
	@SuppressWarnings("rawtypes")
	public static List getJsonStr2List(String jsonString, Class pojoCalss) {
		List result = new ArrayList();
		Object[] dtoArray = JsonUtil.getObjectArray4Json(jsonString);
		//warehouseService.delWarehouseMaterial(warehosueId[0]);// 添加前先删除原有数据
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
	 * 从json HASH表达式中获取�?��map，改map支持嵌套功能
	* @Title: getMap4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return Map    返回类型
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
	 * 从json数组中得到相应java数组
	* @Title: getObjectArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return Object[]    返回类型
	* @author ZhouZhangbao
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		return jsonArray.toArray();

	}

	/** 
	 * 从json对象集合表达式中得到�?��java对象�?
	* @Title: getList4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @param pojoClass
	* @param @return    设定文件
	* @return List    返回类型
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
	 * 从json数组中解析出java字符串数�?
	* @Title: getStringArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return String[]    返回类型
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
	 * 从json数组中解析出javaLong型对象数�?
	* @Title: getLongArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return Long[]    返回类型
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
	 * 从json数组中解析出java Integer型对象数�?
	* @Title: getIntegerArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return Integer[]    返回类型
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
	 * 从json数组中解析出java Date 型对象数组，使用本方法必须保�?
	* @Title: getDateArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @param DataFormat
	* @param @return    设定文件
	* @return Date[]    返回类型
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
	 * 从json数组中解析出java Integer型对象数�?
	* @Title: getDoubleArray4Json
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param jsonString
	* @param @return    设定文件
	* @return Double[]    返回类型
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
	 * 将java对象转换成json字符�?
	* @Title: getJsonString4JavaPOJO
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param javaObj
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {
		String json =JSONObject.toJSONString(javaObj);
		return json;

	}


	/**
	 * 根据表列将字符串数组格式化为json对象
	* @Title: formatBytableCols
	* @Description: TODO(这里用一句话描述这个方法的作�?
	* @param @param obj
	* @param @param tableCols
	* @param @return    设定文件
	* @return String    返回类型
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
	 * 根据表列将集合格式化为json对象
	* @Title: formatBytableCols
	* @Description: TODO(根据表列将集合格式化为json对象)
	* @param @param list
	* @param @param tableCols
	* @param @return    设定文件
	* @return String    返回类型
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
