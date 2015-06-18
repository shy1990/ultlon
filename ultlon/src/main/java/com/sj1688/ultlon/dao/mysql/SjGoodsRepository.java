package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.SjGoods;

public interface SjGoodsRepository extends JpaRepository<SjGoods,Long>{

	/**
	 * 根据条码查询商品相关数据
	 * @param barCode
	 * @return
	 */
	public SjGoods findByBarCode(String barCode);
	
}
