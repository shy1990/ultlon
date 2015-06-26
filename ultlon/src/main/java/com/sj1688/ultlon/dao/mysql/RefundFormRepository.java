package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

public interface RefundFormRepository extends JpaRepository<RefundForm, Long> {
	RefundForm findByTaskForm(TaskForm tf);
}
