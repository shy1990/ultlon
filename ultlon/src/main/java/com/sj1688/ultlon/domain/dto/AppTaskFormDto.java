package com.sj1688.ultlon.domain.dto;

import java.util.Date;

public class AppTaskFormDto {
private String goodsName ;
private String username ;
private Date createdDate;
private String status;
private String type;
private long id;
private String remark;
private String imei;
private String orderNum;

public String getOrderNum() {
	return orderNum;
}
public void setOrderNum(String orderNum) {
	this.orderNum = orderNum;
}
public String getImei() {
	return imei;
}
public void setImei(String imei) {
	this.imei = imei;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

}
