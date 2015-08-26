package com.sj1688.ultlon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj1688.ultlon.service.UserGoodsRecordService;


@Controller
@RequestMapping("/ugrc")
public class UserGoodsRecordController {

	@Autowired
	private UserGoodsRecordService ugrs;
	
	//@ResponseBody
	@RequestMapping(value="/{page}",method = RequestMethod.GET)
	public String list(@PathVariable(value="page")int page,String userName,String goodsName,String startTime,String endTime,Model model){
		Map<String, Object> param = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(userName))
			param.put("userName", userName);
		if(!StringUtils.isEmpty(goodsName))
			param.put("goodsName", goodsName);	
		if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
			param.put("startTime", startTime);
			param.put("endTime", endTime);
		}
		param.put("page", page);
		param.put("rows", 50);  
		System.out.println("参数："+param);
		model.addAttribute("list", ugrs.getUserGoodsRecords(param));
		model.addAttribute("page", page);
		model.addAttribute("countPage",ugrs.getUserGoodsRecordCount(param));
		model.addAttribute("param", param);
		return "/skynet/index";
	}
	
}
