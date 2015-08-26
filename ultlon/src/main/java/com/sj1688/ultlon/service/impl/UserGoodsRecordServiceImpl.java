package com.sj1688.ultlon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.service.UserGoodsRecordService;

@Service("userGoodsRecordService")
public class UserGoodsRecordServiceImpl implements UserGoodsRecordService{

	@Autowired
	private B2BDao dao;
	
	@Override
	public List<Map<String, Object>> getUserGoodsRecords(Map map) {
		return dao.getUserGoodsRecords(map);
	}

	@Override
	public int getUserGoodsRecordCount(Map map) {
		String countStr = dao.getUserGoodsRecordCount(map);
		int count = Integer.parseInt(countStr)/Integer.parseInt(map.get("rows").toString())+1;
		return count;
	}

}
