package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.FinanceForm;
/**
 * 财审单创建事件
 * @author Administrator
 *
 */
public class FinanceFormUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private FinanceForm old;
	public FinanceFormUpdateEvent(FinanceForm source) {
		super(source);
	}
	public FinanceFormUpdateEvent(FinanceForm source, FinanceForm old) {
		super(source);
		this.old = old;
	}
	public FinanceForm getOld() {
		return old;
	}
	public void setOld(FinanceForm old) {
		this.old = old;
	}
	

}
