package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;
//TODO 定义事件demo
public class TestEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public TestEvent(String source) {
		super(source);
	}

}
