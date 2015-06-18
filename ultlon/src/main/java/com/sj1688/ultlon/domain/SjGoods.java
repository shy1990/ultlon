package com.sj1688.ultlon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;
/**
 * 三际所有商品信息
 * @author Administrator
 *
 */

@Entity
@Table(name="tb_sj_goods")
public class SjGoods extends AbstractAuditable<User, Long>{

	private static final long serialVersionUID = 1L;

	private String barCode;//条码
	private String goodsCode;//商品代码
	private String name;//商品名称
	private String shortName;//商品简称
	private String normsCode;//规格代码
	private String color;//颜色
	private String supplier;//供应商
	private String barCodeType;//条码类型
	
	@Column(name = "bar_code")
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@Column(name = "goods_code")
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "short_name")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	@Column(name = "norms_code")
	public String getNormsCode() {
		return normsCode;
	}
	public void setNormsCode(String normsCode) {
		this.normsCode = normsCode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	@Column(name = "bar_code_type")
	public String getBarCodeType() {
		return barCodeType;
	}
	public void setBarCodeType(String barCodeType) {
		this.barCodeType = barCodeType;
	}

	
	
}
