package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.dao.mysql.AfterSaleFormRepository;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.TaskFormUpdateEvent;
/**
 * 退换货单改变的时候，改变售后任务状态和售后单状态
 * @author 武继明
 *
 */
@Component
public class WhenTaskFormStatusChange implements ApplicationListener<TaskFormUpdateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenTaskFormStatusChange.class);
	@Autowired
	private AfterSaleFormRepository afterSaleFormRepository;
	@Override
	public void onApplicationEvent( TaskFormUpdateEvent event) {
		TaskForm taskForm=(TaskForm)event.getSource();
		AfterSaleForm afterForm = taskForm.getAfterSaleForm();
		afterForm.setResult(taskForm.getStatus().toString());
		afterSaleFormRepository.save(afterForm);
		LOG.info("任务状态修改：{}",taskForm);
	}
}
