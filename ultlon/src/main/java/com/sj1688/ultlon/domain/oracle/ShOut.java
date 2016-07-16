 package com.sj1688.ultlon.domain.oracle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 售后出库信息记录
 * @author icodebug
 *
 */

@Entity
@Table(name="SJ_SZ_OUT_STOCK")
public class ShOut {

	@Id
	private String codeId;
	
	@Column(name="order_num")
	private String orderNum;
	
	@Column(name="sku_code")
	private String skuCode;


	

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
	
	
}
