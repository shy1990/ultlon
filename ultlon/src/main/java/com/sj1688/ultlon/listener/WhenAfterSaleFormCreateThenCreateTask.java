package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.AfterSaleFormCreateEvent;
import com.sj1688.ultlon.service.TaskService;
/**
 * 创建售后单的时候创建业务的任务
 * @author 武继明
 *
 */
@Component
public class WhenAfterSaleFormCreateThenCreateTask implements ApplicationListener<AfterSaleFormCreateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenAfterSaleFormCreateThenCreateTask.class);
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( AfterSaleFormCreateEvent event) {
		AfterSaleForm afterSaleForm=(AfterSaleForm)event.getSource();
		TaskForm taskForm = new TaskForm();
		taskForm.setAfterForm(afterSaleForm);
		taskService.save(taskForm);
		LOG.info("售后单：{}",afterSaleForm);
	}
}
