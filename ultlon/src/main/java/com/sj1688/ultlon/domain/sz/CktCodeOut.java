package com.sj1688.ultlon.domain.sz;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CKT_CODE_OUT")
public class CktCodeOut {
	
	@Id
	private String codeID;
	private String config;
	private String remark;
	
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	public String getConfig() {
		
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
