package com.sj1688.ultlon.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LoginLogoutController {
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.UNAUTHORIZED)
	public String login() {
		return "login";
	}


	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

}
