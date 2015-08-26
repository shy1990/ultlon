package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.FinanceFormUpdateEvent;
import com.sj1688.ultlon.service.TaskService;
/**
 * 财审单改变的时候，改变售后任务状态和售后单状态
 * @author peter
 *
 */
@Component
public class WhenFinanceFormStatusChange implements ApplicationListener<FinanceFormUpdateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenFinanceFormStatusChange.class);
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( FinanceFormUpdateEvent event) {
		FinanceForm changeForm=(FinanceForm)event.getSource();
		TaskForm taskForm = changeForm.getTaskForm();
		System.out.println("WhenFinanceFormStatusChange:+++++++++++++++++");
		taskService.updateStatus(taskForm,changeForm.getStatus());
		LOG.info("财审单状态修改：{}",changeForm);
	}
}
