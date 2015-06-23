package com.sj1688.ultlon.domain;

public enum FormAuditStatus {
	
	NOPROCESS("未处理"),
	PROCESSING("处理中"),
	AGREE("同意退款"),
	REJECT("拒绝退款");
	
	private String name;
	

	private FormAuditStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	
	
}
