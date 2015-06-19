package com.sj1688.ultlon.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;
@Entity
@Table(name="tb_repair_form")
public class RepairForm extends AbstractAuditable<User, Long>{
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch=FetchType.EAGER)
	private AfterSaleForm afterSaleForm;
	private BigDecimal cost;
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	private String remark;
	
	
	
	public RepairForm() {
		super();
	}
	public RepairForm(AfterSaleForm afterSaleForm) {
		super();
		this.afterSaleForm = afterSaleForm;
	}
	public AfterSaleForm getAfterSaleForm() {
		return afterSaleForm;
	}
	public void setAfterSaleForm(AfterSaleForm afterSaleForm) {
		this.afterSaleForm = afterSaleForm;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public FormAuditStatus getStatus() {
		return status;
	}
	public void setStatus(FormAuditStatus status) {
		this.status = status;
	}
	
}
