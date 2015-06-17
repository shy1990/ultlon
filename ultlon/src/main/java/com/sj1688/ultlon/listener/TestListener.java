package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.event.TestEvent;
//TODO 事件监听demo
@Component
public class TestListener implements ApplicationListener<TestEvent>{

	@Override
	public void onApplicationEvent(TestEvent event) {
		System.out.println("测试事件广播:"+event.getSource());
	}

}
