package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.AfterSaleForm;

public interface AfterSaleFormRepository extends JpaRepository<AfterSaleForm, Long> {
	public Page<AfterSaleForm> findByUsername(String username,Pageable page);
	
	/**
	 * 根据钱包记录编号获取售后申请信息
	 * @param tradingId
	 * @return
	 */
	AfterSaleForm findByTradingId(String tradingId); 
}
