package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.User;
//TODO 定义事件demo
public class UserLoginEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public UserLoginEvent(User source) {
		super(source);
	}

}
