package com.sj1688.ultlon.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "tb_change_form")
public class ChangeForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	@OneToOne
	private AfterSaleForm afterForm;//售后服务
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	
	/*成交价*/
	private BigDecimal orderPrice;
	private BigDecimal refundMoney;
	private String remark;

	public ChangeForm() {
		super();
	}

	@Transient
	public BigDecimal getRealRefundMoney(){
		//TODO 先判断售后类型，在决定是退最小还是原价退。
		return BigDecimal.TEN;
	}
	public ChangeForm(AfterSaleForm afterForm, BigDecimal orderPrice,
			BigDecimal refundMoney) {
		super();
		this.afterForm = afterForm;
		this.orderPrice = orderPrice;
		this.refundMoney = refundMoney;
	}



	public AfterSaleForm getAfterForm() {
		return afterForm;
	}

	public void setAfterForm(AfterSaleForm afterForm) {
		this.afterForm = afterForm;
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
