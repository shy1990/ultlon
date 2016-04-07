package com.sj1688.ultlon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
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
	
	public Page<TaskForm> getNoProccessTask(Pageable pageable,List<String> areas);
	
	public void updateStatus(TaskForm entity,FormAuditStatus statusToUpdate);
	public List<Map<String,Serializable>> findAllByOrderNum(String orderNum,String skuCode );
	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum);
	public Page<TaskForm> getHistory(Pageable pageable, List<String> regionList);
	public Page<TaskForm> getStatus(Pageable pageable, List<String> regionList,String status);
	public String findMobileByOrderNum1(String orderNum);
}
