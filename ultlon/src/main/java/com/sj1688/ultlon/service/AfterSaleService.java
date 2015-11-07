package com.sj1688.ultlon.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.AfterSaleType;

/**
 * 售后单服务
 * @author Administrator
 *
 */
public interface AfterSaleService {
	/**
	 * 生成指定售后服务
	 * @param imei
	 * @param userId
	 * @return
	 */
	public AfterSaleForm genrateAfterSaleForm(String imei,String userId);
	public void save(AfterSaleForm entity);
	public Page<AfterSaleForm> get(String userId,Pageable page);
	public void update(AfterSaleForm entity);
	/**
	 * 计算可享受服务
	 * @param receiveTime
	 * @param goodsName
	 * @return
	 */
	public List<AfterSaleType> getTypes(Date receiveTime,String goodsName,String skuCode);
	
	/**
	 * 根据钱包记录编号获取售后申请信息
	 * @param tradingId
	 * @return
	 */
	public AfterSaleForm findByTradingId(String tradingId);
	
	
}
