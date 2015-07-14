package com.sj1688.ultlon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

/**
 * 退货服务
 * @author Administrator
 *
 */
public interface RefundService {
	/**
	 * 生成退货单
	 * @param taskForm
	 * @return
	 */
	public RefundForm genrateRefundForm(TaskForm taskForm);
	
	public void save(RefundForm entity);
	
	public Page<RefundForm> get(Pageable page);
	
	public void updateStatus(RefundForm entity,FormAuditStatus statusToUpdate);

	public Page<RefundForm> findAll(Pageable pageable);
	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum);
	/**
	 * 退款
	 * @param form
	 */
	public void refundMoney(RefundForm form);
	
	RefundForm findByTaskForm(TaskForm tf);
}
