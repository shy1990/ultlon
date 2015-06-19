package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.event.RepairFormCreateEvent;
@Component
public class RepairFormCreateEventTestListener implements ApplicationListener<RepairFormCreateEvent>{
	@Override
	public void onApplicationEvent( RepairFormCreateEvent event) {
		RepairForm form=(RepairForm)event.getSource();
		System.out.println("我擦，"+form.getCreatedBy().getUsername()+"既然存了一个维修单。"+form.getAfterSaleForm().getGoodsName());
	}
}
