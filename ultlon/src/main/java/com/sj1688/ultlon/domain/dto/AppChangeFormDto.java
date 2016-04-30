package com.sj1688.ultlon.domain.dto;

import java.util.Date;

public class AppChangeFormDto {
	private String username;
	private String goodsName;
	private String imei;
	private long id;
	private String remark;
	private String orderNum;
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	// private String area;
	//
	//
	// public String getArea() {
	// return area;
	// }
	// public void setArea(String area) {
	// this.area = area;
	// }
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


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
