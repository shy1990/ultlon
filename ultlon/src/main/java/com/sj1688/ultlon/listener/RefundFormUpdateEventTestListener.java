package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
@Component
public class RefundFormUpdateEventTestListener implements ApplicationListener<RefundFormUpdateEvent>{
	@Override
	public void onApplicationEvent( RefundFormUpdateEvent event) {
		RefundForm form=(RefundForm)event.getSource();
		System.out.println("我擦，"+form.getLastModifiedBy().getUsername()+"既然修改一个退货单。"+event.getOld().getStatus()+"-->"+form.getStatus());
	}
}
