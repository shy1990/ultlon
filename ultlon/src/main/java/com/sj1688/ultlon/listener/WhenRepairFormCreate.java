package com.sj1688.ultlon.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.RepairFormCreateEvent;
import com.sj1688.ultlon.service.TaskService;
@Component
public class WhenRepairFormCreate implements ApplicationListener<RepairFormCreateEvent>{
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( RepairFormCreateEvent event) {
		RepairForm form=(RepairForm)event.getSource();
		TaskForm task=form.getTaskForm();
		//任务变成处理中
		taskService.updateStatus(task, FormAuditStatus.PROCESSING);
	}
}
