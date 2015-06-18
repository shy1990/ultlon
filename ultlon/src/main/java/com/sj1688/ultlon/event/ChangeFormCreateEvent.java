package com.sj1688.ultlon.event;

import org.springframework.context.ApplicationEvent;

import com.sj1688.ultlon.domain.ChangeForm;
/**
 * 换货单创建事件
 * @author Administrator
 *
 */
public class ChangeFormCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public ChangeFormCreateEvent(ChangeForm source) {
		super(source);
	}

}
	