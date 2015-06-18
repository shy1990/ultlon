package com.sj1688.ultlon.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.event.AfterSaleFormCreateEvent;
//TODO 事件监听demo
@Component
public class TestListener implements ApplicationListener<AfterSaleFormCreateEvent>{

	@Override
	public void onApplicationEvent( AfterSaleFormCreateEvent event) {
		AfterSaleForm source = (AfterSaleForm) event.getSource();
		System.out.println(source.getUsername()+"申请了:"+source.getImei()+source.getGoodsName());
	}

}
