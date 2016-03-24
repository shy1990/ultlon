package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

public interface RefundFormRepository extends JpaRepository<RefundForm, Long> {
	RefundForm findByTaskForm(TaskForm tf);
	
	@Query("select a from RefundForm a where a.taskForm.afterSaleForm.imei=?1")
	Page<RefundForm> findByImei(String imei,Pageable pageable);
}
