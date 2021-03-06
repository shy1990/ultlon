package com.sj1688.ultlon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value={"","/"})
	public String home(){
		return "index";
	}
	@RequestMapping(value={"/admin"})
	public String admin(){
		return "admin";
	}

	@RequestMapping(value={"/app"})
	public String app(){
		return "app";
	}
	@RequestMapping(value={"/unauthorized"})
	public String unauthorized(){
		return "unauthorized";
	}
	
	@RequestMapping(value={"/register"})
	public String register(){
		return "register";
	}
	
	@RequestMapping(value={"/finance"})
	public String finance(){
		return "finance";
	}
	
}
