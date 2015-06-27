package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;

public interface RepairFormRepository extends JpaRepository<RepairForm, Long> {
	RepairForm findByTaskForm(TaskForm taskForm);
}
