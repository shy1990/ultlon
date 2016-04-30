package com.sj1688.ultlon.dao.oracle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sj1688.ultlon.domain.Regions;


public interface RegionsMapper {
    
	/**
	 * 根据父ID查询子类
	* @Title: gainRegionByPid
	* @param @param pid
	* @param @return    设定文件
	* @return List<Regions>    返回类型
	* @author ZhouZhangbao
	 */
    public List<Regions> gainRegionByPid(Map<String,Object> pids);
   
}