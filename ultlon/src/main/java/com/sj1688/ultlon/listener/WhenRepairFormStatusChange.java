package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.RepairFormUpdateEvent;
import com.sj1688.ultlon.service.TaskService;
/**
 * 换新单改变的时候，改变售后任务状态和售后单状态
 * @author 武继明
 *
 */
@Component
public class WhenRepairFormStatusChange implements ApplicationListener<RepairFormUpdateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenRepairFormStatusChange.class);
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( RepairFormUpdateEvent event) {
		RepairForm changeForm=(RepairForm)event.getSource();
		TaskForm taskForm = changeForm.getTaskForm();
		taskService.updateStatus(taskForm,changeForm.getStatus());
		LOG.info("维修单状态修改：{}",changeForm);
	}
}
