package com.sj1688.ultlon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.dao.oracle.RegionsMapper;
import com.sj1688.ultlon.domain.Regions;
import com.sj1688.ultlon.service.RegionsService;
import com.sj1688.ultlon.util.ToolsUtil;
@Service
public class RegionsServiceImpl implements RegionsService {
	@Autowired
	private RegionsMapper regionsMapper;

	public List<Regions> gainRegionByPid(String pids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", pids);
		map.put("list", ToolsUtil.StringConvertList(pids));
		
		return regionsMapper.gainRegionByPid(map);
	}

}
