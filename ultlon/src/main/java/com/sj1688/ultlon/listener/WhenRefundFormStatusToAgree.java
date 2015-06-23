package com.sj1688.ultlon.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.RefundService;
/**
 * 当退货单状态改变成同意的时候，退款
 * @author Administrator
 *
 */
@Component
public class WhenRefundFormStatusToAgree implements ApplicationListener<RefundFormUpdateEvent>{
	@Autowired
	private RefundService refundService;
	@Override
	public void onApplicationEvent( RefundFormUpdateEvent event) {
		RefundForm form=(RefundForm)event.getSource();
		boolean agree = form.getStatus().equals(FormAuditStatus.AGREE);
		if(agree){
			System.out.println("我擦 退款啦！"+form.getTaskForm().getAfterSaleForm().getImei());
			refundService.refundMoney(form);
		}
	}
}
