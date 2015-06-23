package com.sj1688.ultlon.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.ChangeFormCreateEvent;
import com.sj1688.ultlon.service.TaskService;
@Component
public class WhenChangeFormCreate implements ApplicationListener<ChangeFormCreateEvent>{
	@Autowired
	private TaskService taskService;
	@Override
	public void onApplicationEvent( ChangeFormCreateEvent event) {
		
		ChangeForm form=(ChangeForm)event.getSource();
		TaskForm task=form.getTaskForm();
		//任务变成处理中
		taskService.updateStatus(task, FormAuditStatus.PROCESSING);
	}
}
