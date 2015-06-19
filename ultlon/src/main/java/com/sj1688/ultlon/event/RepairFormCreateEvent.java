package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.RepairForm;
/**
 * 维修单创建事件
 * @author Administrator
 *
 */
public class RepairFormCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public RepairFormCreateEvent(RepairForm source) {
		super(source);
	}

}
