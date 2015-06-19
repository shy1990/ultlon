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
@Table(name = "tb_refund_form")
public class RefundForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch=FetchType.EAGER)
	private TaskForm taskForm;//售后任务
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	
	/*成交价*/
	private BigDecimal orderPrice;
	private BigDecimal refundMoney;
	private String remark;

	public RefundForm() {
		super();
	}

	@Transient
	public BigDecimal getRealRefundMoney(){
		//TODO 宋保真 ----> 先判断售后类型，在决定是退最小还是原价退。
		return BigDecimal.TEN;
	}
	
	public RefundForm(TaskForm taskForm, BigDecimal orderPrice,BigDecimal refundMoney) {
		super();
		this.taskForm = taskForm;
		this.orderPrice = orderPrice;
		this.refundMoney = refundMoney;
	}


	public TaskForm getTaskForm() {
		return taskForm;
	}

	public void setTaskForm(TaskForm taskForm) {
		this.taskForm = taskForm;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
}
