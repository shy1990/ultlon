package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.TaskForm;
/**
 * 创建app推送事件
 * @author xq
 *
 */
public class AppMsgEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppMsgEvent(TaskForm taskForm) {
		super(taskForm);
	}

}
