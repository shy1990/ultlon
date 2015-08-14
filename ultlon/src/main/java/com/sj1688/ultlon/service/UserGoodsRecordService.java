package com.sj1688.ultlon.service;

import java.util.List;
import java.util.Map;

public interface UserGoodsRecordService {
	/**
	 * 获取用户浏览记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserGoodsRecords(Map map);
	
	/**
	 * 获取用户浏览记录总个数
	 * @param map
	 * @return
	 */
	int getUserGoodsRecordCount(Map map);
}
