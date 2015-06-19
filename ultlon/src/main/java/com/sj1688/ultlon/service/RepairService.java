package com.sj1688.ultlon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RepairForm;

/**
 * 维修服务
 * @author Administrator
 *
 */
public interface RepairService {
	/**
	 * 生成维修单
	 * @param afterSaleForm
	 * @return
	 */
	public RepairForm genrateRepairForm(AfterSaleForm afterSaleForm);
	
	public void save(RepairForm entity);
	
	public Page<RepairForm> get(Pageable page);
	
	public void updateStatus(RepairForm entity,FormAuditStatus statusToUpdate);

	public Page<RepairForm> findAll(Pageable pageable);
}
