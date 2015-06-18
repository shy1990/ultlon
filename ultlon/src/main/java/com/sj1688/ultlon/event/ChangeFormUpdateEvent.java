package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.ChangeForm;
/**
 * 換单创建事件
 * @author Administrator
 *
 */
public class ChangeFormUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private ChangeForm old;
	public ChangeFormUpdateEvent(ChangeForm source) {
		super(source);
	}
	public ChangeFormUpdateEvent(ChangeForm source, ChangeForm old) {
		super(source);
		this.old = old;
	}
	public ChangeForm getOld() {
		return old;
	}
	public void setOld(ChangeForm old) {
		this.old = old;
	}
	

}
