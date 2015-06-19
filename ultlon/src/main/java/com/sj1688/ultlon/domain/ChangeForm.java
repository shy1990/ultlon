package com.sj1688.ultlon.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "tb_change_form")
public class ChangeForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	@OneToOne
	private AfterSaleForm afterForm;//售后服务
	
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	
	private String remark;

	public ChangeForm() {
		super();
	}

	public ChangeForm(AfterSaleForm afterForm) {
		super();
		this.afterForm = afterForm;
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


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}