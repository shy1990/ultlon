package com.sj1688.ultlon.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AppRefundFormDto {
//根据refund中list.jsp写的
	private String username;
	private String goodsName;
	private String  imei;
	private String orderNum;
//	private Date receiveTime;
	private BigDecimal realRefundMoney;
//	private String type;
	private String remark;
//	private String status;
	private long id;
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	private BigDecimal orderPrice;
	private BigDecimal currentPrice;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
//	public Date getReceiveTime() {
//		return receiveTime;
//	}
//	public void setReceiveTime(Date receiveTime) {
//		this.receiveTime = receiveTime;
//	}
	public BigDecimal getRealRefundMoney() {
		return realRefundMoney;
	}
	public void setRealRefundMoney(BigDecimal realRefundMoney) {
		this.realRefundMoney = realRefundMoney;
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
}
