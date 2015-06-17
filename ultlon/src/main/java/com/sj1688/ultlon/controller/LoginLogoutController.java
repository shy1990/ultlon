package com.sj1688.ultlon.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.event.UserLoginEvent;

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
	public String login(String username, String password, @RequestParam(defaultValue="false")Boolean rememberMe) {
		System.out.println(username+":"+password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password, rememberMe);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			return "redirect:/login";
		}
		ctx.publishEvent(new UserLoginEvent((User) SecurityUtils.getSubject().getPrincipal()));
		return "redirect:/";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

}
