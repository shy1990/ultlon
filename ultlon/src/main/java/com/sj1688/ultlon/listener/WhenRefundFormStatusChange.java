package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.TaskService;
/**
 * 退换货单改变的时候，改变售后任务状态和售后单状态
 * @author 武继明
 *
 */
@Component
public class WhenRefundFormStatusChange implements ApplicationListener<RefundFormUpdateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenRefundFormStatusChange.class);
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( RefundFormUpdateEvent event) {
		RefundForm changeForm=(RefundForm)event.getSource();
		TaskForm taskForm = changeForm.getTaskForm();
		System.out.println("WhenRefundFormStatusChange:+++++++++++++++++");
		taskService.updateStatus(taskForm,changeForm.getStatus());
		LOG.info("退货单状态修改：{}",changeForm);
	}
}
