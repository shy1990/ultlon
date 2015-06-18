package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.RefundForm;
/**
 * 退货单创建事件
 * @author Administrator
 *
 */
public class RefundFormCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public RefundFormCreateEvent(RefundForm source) {
		super(source);
	}

}
