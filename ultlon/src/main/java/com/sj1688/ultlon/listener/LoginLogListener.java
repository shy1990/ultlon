package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.event.UserLoginEvent;
@Component
public class LoginLogListener implements ApplicationListener<UserLoginEvent>{
	@Override
	public void onApplicationEvent( UserLoginEvent event) {
		User user=(User)event.getSource();
		System.out.println("我操，你登陆了。"+user.getId());
	}
}
