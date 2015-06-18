package com.sj1688.ultlon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;
/**
 * b2b销售订单记录
 * @author Administrator
 *
 */

@Entity
@Table(name="tb_after_sale_order")
public class AfterSaleOrder extends AbstractAuditable<User, Long>{

	private static final long serialVersionUID = 1L;
	private String imei;//串号
	private String barCode;//条码
	private String ecerpNo;//（dd号）
	private String normsCode;//商品代码
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Column(name = "bar_code")
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	@Column(name = "ecerp_no")
	public String getEcerpNo() {
		return ecerpNo;
	}
	public void setEcerpNo(String ecerpNo) {
		this.ecerpNo = ecerpNo;
	}
	
	@Column(name = "norms_code")
	public String getNormsCode() {
		return normsCode;
	}
	public void setNormsCode(String normsCode) {
		this.normsCode = normsCode;
	}
	


	
	
	
}
