package com.sj1688.ultlon.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.event.TestEvent;

@Controller
public class IndexController {
	@Autowired
	private ApplicationContext ctx;
	@RequestMapping(value={"","/"})
	public String home(){
		//TODO 代码获取登录用户demo
		User currentUser=(User)SecurityUtils.getSubject().getPrincipal();
		//TODO 事件发布demo
		ctx.publishEvent(new TestEvent("有人访问首页"+currentUser.getUsername()));
		return "index";
	}

}
