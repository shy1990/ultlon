package com.sj1688.ultlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj1688.ultlon.domain.Regions;
import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.service.RegionsService;
import com.sj1688.ultlon.service.UserService;
import com.sj1688.ultlon.util.ToolsUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService  userService;
	@Autowired
	private RegionsService regionsService;
	
	@ResponseBody
	@RequestMapping(value = "getRegions", method = RequestMethod.POST)
	public List<Regions> getRegions(@RequestParam(value="pid")String pid){
		
		List<Regions> map =  regionsService.gainRegionByPid(pid);
	    return map;	
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user) {
		userService.save(user); 
		return "register/success";
	}

}
