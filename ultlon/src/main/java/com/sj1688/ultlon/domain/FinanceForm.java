package com.sj1688.ultlon.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "tb_finance_form")
public class FinanceForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;
	@OneToOne(fetch=FetchType.EAGER)
	private TaskForm taskForm;

	@OneToOne(fetch=FetchType.EAGER)
	private RefundForm refundForm;//售后任务
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	
	/*成交价*/
	private BigDecimal orderPrice;
	
	private BigDecimal currentPrice;
	private BigDecimal realPrice;
	private String remark;

	public FinanceForm() {
		super();
	}
	
	public FinanceForm(TaskForm taskForm, BigDecimal currentPrice) {
		super();
		this.taskForm = taskForm;
		this.currentPrice = currentPrice;
	}
	
	public TaskForm getTaskForm() {
		return taskForm;
	}

	public void setTaskForm(TaskForm taskForm) {
		this.taskForm = taskForm;
	}
	
	public RefundForm getRefundForm() {
		return refundForm;
	}

	public void setRefundForm(RefundForm refundForm) {
		this.refundForm = refundForm;
	}

	public FormAuditStatus getStatus() {
		return status;
	}

	public void setStatus(FormAuditStatus status) {
		this.status = status;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	
	
}
