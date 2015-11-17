package com.sj1688.ultlon.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.FormAuditStatus;

/**
 * 财审服务
 * @author Administrator
 *
 */
public interface FinanceService {
	/**
	 * 生成财审单
	 * @param taskForm
	 * @return
	 */
	
	public void update(FinanceForm entity) ;
	
	public Page<FinanceForm> get(Pageable page);
	
	public void updateStatus(FinanceForm entity,FormAuditStatus statusToUpdate,String remark,BigDecimal cost);
	
	public void updatePoint(String username,BigDecimal orderPrice);

	public Page<FinanceForm> findAll(Pageable pageable);
//	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum);
	
	/**
	 * 根据订单编号获取管易编号
	 * @param orderNum
	 * @return
	 */
	public Map<String, String> findByOrderNum(String orderNum);
	
}
