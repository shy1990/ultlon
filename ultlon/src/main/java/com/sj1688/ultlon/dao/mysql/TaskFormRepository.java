package com.sj1688.ultlon.dao.mysql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;

public interface TaskFormRepository extends JpaRepository<TaskForm, Long> {
	public Page<TaskForm> findByAreaIn(List<String> areas,Pageable page);
	public TaskForm findByAfterForm(AfterSaleForm afterSaleForm);
}
