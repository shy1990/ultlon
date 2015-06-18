package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.event.RefundFormCreateEvent;
@Component
public class RefundFormCreateEventTestListener implements ApplicationListener<RefundFormCreateEvent>{
	@Override
	public void onApplicationEvent( RefundFormCreateEvent event) {
		RefundForm form=(RefundForm)event.getSource();
		System.out.println("我擦，"+form.getCreatedBy().getUsername()+"既然存了一个退货单。"+form.getAfterForm().getGoodsName());
	}
}
