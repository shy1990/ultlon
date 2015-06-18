package com.sj1688.ultlon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;

/**
 * 退货服务
 * @author Administrator
 *
 */
public interface RefundService {
	/**
	 * 生成退货单
	 * @param afterSaleForm
	 * @return
	 */
	public RefundForm genrateRefundForm(AfterSaleForm afterSaleForm);
	
	public void save(RefundForm entity);
	
	public Page<RefundForm> get(Pageable page);
	
	public void updateStatus(RefundForm entity,FormAuditStatus statusToUpdate);

	public Page<RefundForm> findAll(Pageable pageable);
}
