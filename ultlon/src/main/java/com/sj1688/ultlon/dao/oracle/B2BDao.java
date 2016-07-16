package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface B2BDao {
	public List<Map<String, Serializable>> findSomthing(
			@Param("name") String name);

	/**
	 * 查询 订单号，签收时间，商品名称
	 * 
	 * @param skucode
	 * @param ddNum
	 * @return ORDER_NUM RECEIVE_TIME GOODS_NAME
	 */
	public Map<String, Serializable> findNRGBy(
			@Param("skucode") String skucode, @Param("ddNum") String ddNum);

	/**
	 * 根据订单编号获取管易编号
	 * @param orderNum
	 * @return
	 */
	public Map<String, String> findByOrderNum(String orderNum);
	
	/**
	 * @param skuCode
	 * @param orderNum
	 * @return ORDER_PRICE,CURRENT_PRICE
	 */
	public Map<String, BigDecimal> findOrderPirceAndCurrentPrice(
			@Param("skuCode") String skuCode, @Param("orderNum") String orderNum);

	/**
	 * @param username
	 * @return
	 */
	public List<String> findUserArea(String username);
	
	/**
	 * 根据用户名查找业务编号 
	 * @param username
	 * @return
	 */
	public List<String> findYewuIdByUsername(String username);

	/**
	 * 通过orderNum 查找订单id
	 * 
	 * @param orderNum
	 * @return
	 */
	public List<Map<String, Serializable>> findAllByOrderNum(
			@Param("orderNum") String orderNum, @Param("skuCode") String skuCode);
	
	/**
	 * 通过orderNum 查找客户手机号
	 * 
	 * @param orderNum
	 * @return
	 */
	public List<Map<String, Serializable>> findMobileByOrderNum(
			@Param("orderNum") String orderNum);
	public String findMobileByOrderNum1(String orderNum);

	/**
	 * 根据订单号和商品号查询 地址，签收时间，商品名称
	 * 
	 * @param orderNum
	 * @return ORDER_NUM RECEIVE_TIME GOODS_NAME
	 */
	public String findOrderId(String orderNum);

	/**
	 * 根据用户名和订单管易编号获取订单信息
	 * 
	 * @param userId
	 *            用户Id
	 * @param ecerpNo
	 *            订单编号
	 * @return
	 */
	Map<String, Object> selectByUidAndErp(@Param("userId") String userId,
			@Param("ecerpNo") String ecerpNo);

	/**
	 * 根据用户名和订单管易编号和商品id获取订单信息
	 * 
	 * @param userId
	 *            用户Id
	 * @param ecerpNo
	 *            订单编号
	 * @return
	 */
	Map<String, Object> selectByUidAndErpAndGoodsId(
			@Param("userId") String userId, @Param("ecerpNo") String ecerpNo,
			@Param("goodsId") String goodsId);

	/**
	 * 根据规格代码获取商品信息
	 * 
	 * @param skuNum
	 * @return
	 */
	Map<String, Object> getGoodsBySkunum(@Param("skuNum") String skuNum);

	/**
	 * 根据管易编号和商品id获取订单信息
	 * 
	 * @param ecerpNo
	 * @param goodsId
	 * @return
	 */
	Map<String, Object> selectByArea(@Param("ecerpNo") String ecerpNo,
			@Param("goodsId") String goodsId);
	
	
	/**
	 * 根据用户名查询用户积分
	 * @param username
	 * @return
	 */
	public int findPointByUsername(String username);
	
	/**
	 * 根据用户名修改用户积分
	 * @param username
	 * @return
	 */
	public void updatePoint(@Param("username") String username,@Param("point") int point);
	
	/**
	 * 获取用户浏览记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserGoodsRecords(Map map);
	
	/**
	 * 获取用户浏览记录总个数
	 * @param map
	 * @return
	 */
	String getUserGoodsRecordCount(Map map);
	
	/**
	 * 查询神舟的出库串码数据
	 * @param imei
	 * @return
	 */
	List<Map<String, Object>> findSzImei(String imei);
	/**
	 * 查询管易的出库串码数据
	 * @param imei
	 * @return
	 */
	List<Map<String, Object>> findGyImei(String imei);
}
