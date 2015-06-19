package com.sj1688.ultlon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj1688.ultlon.dao.mysql.AfterSaleFormRepository;
import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.ChangeFormUpdateEvent;
/**
 * 换新单改变的时候，改变售后任务状态和售后单状态
 * @author 武继明
 *
 */
@Component
public class WhenChangeFormStatusChange implements ApplicationListener<ChangeFormUpdateEvent>{
	private static final Logger LOG=LoggerFactory.getLogger(WhenChangeFormStatusChange.class);
	@Autowired
	private TaskFormRepository taskRepository;
	@Autowired
	private AfterSaleFormRepository afterSaleFormRepository;
	@Override
	public void onApplicationEvent( ChangeFormUpdateEvent event) {
		ChangeForm changeForm=(ChangeForm)event.getSource();
		AfterSaleForm afterForm = changeForm.getAfterForm();
		afterForm.setResult(changeForm.getStatus().toString());
		afterSaleFormRepository.save(afterForm);
		
		TaskForm taskForm = taskRepository.findByAfterForm(afterForm);
		taskForm.setStatus(changeForm.getStatus());
		taskRepository.save(taskForm);
		LOG.info("换新单状态修改：{}",changeForm);
	}
}
