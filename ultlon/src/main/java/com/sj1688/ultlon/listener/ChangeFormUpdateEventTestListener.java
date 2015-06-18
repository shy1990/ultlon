package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.event.ChangeFormUpdateEvent;
@Component
public class ChangeFormUpdateEventTestListener implements ApplicationListener<ChangeFormUpdateEvent>{
	@Override
	public void onApplicationEvent( ChangeFormUpdateEvent event) {
		ChangeForm form=(ChangeForm)event.getSource();
		System.out.println("我擦，"+form.getLastModifiedBy().getUsername()+"既然修改一个換新单。"+event.getOld().getStatus()+"-->"+form.getStatus());
	}
}
