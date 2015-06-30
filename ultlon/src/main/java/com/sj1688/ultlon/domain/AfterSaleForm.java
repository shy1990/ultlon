package com.sj1688.ultlon.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractAuditable;
@Entity
@Table(name="tb_after_sale_form")
public class AfterSaleForm extends AbstractAuditable<User, Long>{
	private static final long serialVersionUID = 1L;
	private String imei;
	private String orderNum;//订单号
	@Enumerated(EnumType.STRING)
	private AfterSaleType type;
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveTime;
	private String skuCode;
	private String goodsName;
	private String result="未处理";//处理结果
	private String remark;
	private String username;
	
	

	public AfterSaleForm() {
		super();
	}
	public AfterSaleForm(String imei, String username) {
		super();
		this.imei = imei;
		this.username = username;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public AfterSaleType getType() {
		return type;
	}
	public void setType(AfterSaleType type) {
		this.type = type;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
