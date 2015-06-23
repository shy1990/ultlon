package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.TaskForm;
/**
 * 任务完成事件
 * @author Administrator
 *
 */
public class TaskFormUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private TaskForm old;
	public TaskFormUpdateEvent(TaskForm source) {
		super(source);
	}
	public TaskFormUpdateEvent(TaskForm source, TaskForm old) {
		super(source);
		this.old = old;
	}
	public TaskForm getOld() {
		return old;
	}
	public void setOld(TaskForm old) {
		this.old = old;
	}
	

}
