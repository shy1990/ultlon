package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.RefundForm;
/**
 * 退货单创建事件
 * @author Administrator
 *
 */
public class RefundUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private RefundForm old;
	public RefundUpdateEvent(RefundForm source) {
		super(source);
	}
	public RefundUpdateEvent(RefundForm source, RefundForm old) {
		super(source);
		this.old = old;
	}
	public RefundForm getOld() {
		return old;
	}
	public void setOld(RefundForm old) {
		this.old = old;
	}
	

}
