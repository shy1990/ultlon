package com.sj1688.ultlon.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;
@Entity
@Table(name="tb_repair_form")
public class RepairForm extends AbstractAuditable<User, Long>{
	private static final long serialVersionUID = 1L;
	private String imei;
	private String remark;
	private String username;
	
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
	
}
