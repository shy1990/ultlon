package com.sj1688.ultlon.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

/**
 * 退货服务
 * 
 * @author Administrator
 *
 */
public interface RefundService {
	/**
	 * 生成退货单
	 * 
	 * @param taskForm
	 * @return
	 */
	public RefundForm genrateRefundForm(TaskForm taskForm);

	public void save(RefundForm entity);

	public void save(FinanceForm financeForm);

	public void update(RefundForm entity);

	public Page<RefundForm> get(Pageable page);

	public void updateStatus(RefundForm entity, FormAuditStatus statusToUpdate,
			String remark);

	public Page<RefundForm> findAll(Pageable pageable);
	
	public Page<RefundForm> findAllByImei(String imei, Pageable pageable);

	public Page<RefundForm> findAllByUserName(String username, Pageable pageable);

	public Page<RefundForm> findAllByOrderNum(String orderNum, Pageable pageable);

	public Page<RefundForm> findByModifiedDateBetween(Date startDate,Date endDate, Pageable pageable);

	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum);

	/**
	 * 退款
	 * 
	 * @param form
	 */
	public void refundMoney(RefundForm form);

	RefundForm findByTaskForm(TaskForm tf);

	public FinanceForm genrateFinanceForm(TaskForm taskForm);
}
