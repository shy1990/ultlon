package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface B2BDao {
    public List<Map<String,Serializable>> findSomthing(@Param("name")String name );
    /**
     *  查询 订单号，签收时间，商品名称 
     * @param skucode
     * @param ddNum
     * @return ORDER_NUM  RECEIVE_TIME GOODS_NAME
     */
	public Map<String, Serializable> findNRGBy(@Param("skucode")String skucode,@Param("ddNum") String ddNum);
	/**
	 * //TODO 宋保真 ----> 获取成交价和现价
	 * @param skuCode
	 * @param orderNum
	 * @return ORDER_PRICE,CURRENT_PRICE
	 */
	public Map<String, BigDecimal> findOrderPirceAndCurrentPrice(
			@Param("skucode")String skuCode, @Param("orderNum")String orderNum);
	/**
	 *  //TODO 张泽林 ----> 获取用户区域 
	 * @param username
	 * @return
	 */
	public String findUserArea(String username);
}