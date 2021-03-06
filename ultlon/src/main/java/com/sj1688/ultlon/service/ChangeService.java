package com.sj1688.ultlon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

/**
 * 换新服务
 * @author Administrator
 *
 */
public interface ChangeService {
	/**
	 * 生成换新单
	 * @param taskForm
	 * @return
	 */
	public ChangeForm genrateChangeForm(TaskForm taskForm);
	
	public void save(ChangeForm entity);
	
	public Page<ChangeForm> get(Pageable page);
	
	public void updateStatus(ChangeForm entity,FormAuditStatus statusToUpdate,String remark);

	public Page<ChangeForm> findAll(String imei,Pageable pageable);
	
	public Page<ChangeForm> findAll2(String username,Pageable pageable);
}
