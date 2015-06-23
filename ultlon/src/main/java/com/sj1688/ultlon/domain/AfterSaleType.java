package com.sj1688.ultlon.domain;



/**
 * 售后类型
 * @author Administrator
 *
 */
public enum AfterSaleType {
	KXS("开箱损"),THH30("30天退换货"),WX("维修"),DMDHX100("多美达百日换新");
	
	private String name;

	private AfterSaleType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}


	
}
