package com.sj1688.ultlon.service;

import java.util.Map;

import com.sj1688.ultlon.domain.AfterSaleOrder;


public interface AfterSaleOrderService {
	/**
	 * 根据串码获取记录
	 * @param imei 串码
	 * @return
	 */
	public AfterSaleOrder findByImei(String imei);
	
	
	/**
	 * 根据串号和用户id获取订单详情
	 * @param imei 串号
	 * @param userId 用户id
	 * @return
	 */
	public Map<String , Object> getOrder(String imei,String userId);
	
	/**
	 * 保存一个记录
	 * @param uo
	 * @return
	 */
	public AfterSaleOrder save(AfterSaleOrder uo);
	
	 /**
     * 根据用户名和订单管易编号和商品id获取订单信息
     * @param userId 用户Id
     * @param ecerpNo 订单编号
     * @return
     */
    public Map<String,Object> selectByUidAndErpAndGoodsId(String userId,String ecerpNo,String goodsId);
    
    /**
     * 根据规格代码获取商品信息
     * @param skuNum
     * @return
     */
    public Map<String,Object> getGoodsBySkunum(String skuNum);
    
    /**
     * 根据管易编号和商品id获取订单信息
     * @param ecerpNo
     * @param goodsId
     * @return
     */
    Map<String,Object> selectByArea(String ecerpNo,String goodsId);
}
