package com.sj1688.ultlon.dao.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.AfterSaleOrder;



public interface AfterSaleOrderRepository extends JpaRepository<AfterSaleOrder,Long>{
	
	/**
	 * 根据串码获取订单编号
	 * @param imei
	 * @return
	 */
	public List<AfterSaleOrder> findByImeiOrderByCreatedDateDesc(String imei); 
	
   
	
}
