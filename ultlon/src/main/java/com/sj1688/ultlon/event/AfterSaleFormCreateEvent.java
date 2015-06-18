package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.AfterSaleForm;
/**
 * 售后服务创建事件
 * @author Administrator
 *
 */
public class AfterSaleFormCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public AfterSaleFormCreateEvent(AfterSaleForm source) {
		super(source);
	}

}
