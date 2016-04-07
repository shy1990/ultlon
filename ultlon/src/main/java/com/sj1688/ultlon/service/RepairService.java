package com.sj1688.ultlon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;

/**
 * 维修服务
 * @author Administrator
 *
 */
public interface RepairService {
	/**
	 * 生成维修单
	 * @param taskForm
	 * @return
	 */
	public RepairForm genrateRepairForm(TaskForm taskForm);
	
	public void save(RepairForm entity);
	public void update(RepairForm entity) ;
	
	public Page<RepairForm> get(Pageable page);
	
	public void updateStatus(RepairForm entity,FormAuditStatus statusToUpdate,String remark,String track_no);

	public Page<RepairForm> findAll(String imei,Pageable pageable);
	
	public Page<RepairForm> findAll2(String imei,Pageable pageable);
	RepairForm findByTaskForm(TaskForm taskForm);
}

