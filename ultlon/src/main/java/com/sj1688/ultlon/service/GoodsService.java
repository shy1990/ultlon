package com.sj1688.ultlon.service;

import com.sj1688.ultlon.domain.Goods;


public interface GoodsService {

	
	public Goods save(Goods g);
	
	/**
	 * 根据条码查询商品相关数据
	 * @param barCode
	 * @return
	 */
	public Goods findByBarCode(String barCode);
	
}
