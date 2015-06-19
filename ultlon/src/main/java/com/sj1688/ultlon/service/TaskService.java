package com.sj1688.ultlon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;

/**
 * 售后任务服务
 * @author 武继明
 *
 */
public interface TaskService {
	/**
	 * 任务
	 * @param afterSaleForm
	 * @param area
	 * @return
	 */
	public TaskForm genrateForm(AfterSaleForm afterSaleForm);
	
	public void save(TaskForm entity);
	
	public Page<TaskForm> get(Pageable pageable,List<String> areas);
	
	public void reject(TaskForm entity);

}
