package com.sj1688.ultlon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.ChangeForm;

/**
 * 换新服务
 * @author Administrator
 *
 */
public interface ChangeService {
	/**
	 * 生成换新单
	 * @param afterSaleForm
	 * @return
	 */
	public ChangeForm genrateChangeForm(AfterSaleForm afterSaleForm);
	
	public void save(ChangeForm entity);
	
	public Page<ChangeForm> get(Pageable page);
	
	public void updateStatus(ChangeForm entity,FormAuditStatus statusToUpdate);

	public Page<ChangeForm> findAll(Pageable pageable);
}
