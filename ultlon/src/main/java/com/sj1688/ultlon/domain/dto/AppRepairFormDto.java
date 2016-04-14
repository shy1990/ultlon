package com.sj1688.ultlon.domain.dto;

import java.math.BigDecimal;
import java.util.Date;


public class AppRepairFormDto {

	private String username;
	private String goodsName;
	private String  imei;
	private BigDecimal cost;
//	private String track_no;
	private String remark;
	private String status;
	private long id;
	private Date createdDate;
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
//	public String getTrack_no() {
//		return track_no;
//	}
//	public void setTrack_no(String track_no) {
//		this.track_no = track_no;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
