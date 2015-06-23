package com.sj1688.ultlon.domain;

public enum FormAuditStatus {
	
	NOPROCESS{public String getName(){return "未处理";}},
	AGREE{public String getName(){return "同意退款";}},
	REJECT{public String getName(){return "拒绝退款";}};
     public abstract String getName();
}
