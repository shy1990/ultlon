package com.sj1688.ultlon.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "账户不存在!";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名或密码错误!";
		} else if (AuthenticationException.class.getName().equals(exceptionClassName)){
		  error ="认证错误!";
		}else if(exceptionClassName != null) {
		  error ="认证错误!";
			System.out.println("其他错误：" + exceptionClassName);
		}
		model.addAttribute("error", error);
		return "login";
	}


	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

}
