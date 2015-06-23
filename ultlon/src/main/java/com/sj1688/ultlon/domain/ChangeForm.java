package com.sj1688.ultlon.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "tb_change_form")
public class ChangeForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch=FetchType.EAGER)
	private TaskForm taskForm;//售后任务
	
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	
	private String remark;

	
	public ChangeForm(TaskForm taskForm) {
		super();
		this.taskForm = taskForm;
	}

	public ChangeForm() {
		super();
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


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
