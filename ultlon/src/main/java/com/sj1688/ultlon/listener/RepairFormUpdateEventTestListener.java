package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.event.RepairFormUpdateEvent;
@Component
public class RepairFormUpdateEventTestListener implements ApplicationListener<RepairFormUpdateEvent>{
	@Override
	public void onApplicationEvent( RepairFormUpdateEvent event) {
		RepairForm form=(RepairForm)event.getSource();
		System.out.println("我擦，"+form.getLastModifiedBy().getUsername()+"既然修改一个维修单。"+event.getOld().getStatus()+"-->"+form.getStatus());
	}
}
