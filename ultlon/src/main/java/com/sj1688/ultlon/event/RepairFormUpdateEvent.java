package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.RepairForm;
/**
 * 退货单创建事件
 * @author Administrator
 *
 */
public class RepairFormUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private RepairForm old;
	public RepairFormUpdateEvent(RepairForm source) {
		super(source);
	}
	public RepairFormUpdateEvent(RepairForm source, RepairForm old) {
		super(source);
		this.old = old;
	}
	public RepairForm getOld() {
		return old;
	}
	public void setOld(RepairForm old) {
		this.old = old;
	}
	

}
