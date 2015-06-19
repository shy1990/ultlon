package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.event.ChangeFormCreateEvent;
@Component
public class ChangeFormCreateEventTestListener implements ApplicationListener<ChangeFormCreateEvent>{
	@Override
	public void onApplicationEvent( ChangeFormCreateEvent event) {
		ChangeForm form=(ChangeForm)event.getSource();
		System.out.println("我擦，"+form.getCreatedBy().getUsername()+"既然存了一个換新单。"+form.getTaskForm().getAfterSaleForm().getGoodsName());
	}
}
