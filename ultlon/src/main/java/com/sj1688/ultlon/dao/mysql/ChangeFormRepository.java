package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;


public interface ChangeFormRepository extends JpaRepository<ChangeForm, Long> {
	ChangeForm findByTaskForm(TaskForm tf);

	@Query("select a from ChangeForm a where a.taskForm.afterSaleForm.imei=?1")
	Page<ChangeForm> findByImei(String imei, Pageable pageable);
	
	@Query("select a from ChangeForm a where a.taskForm.afterSaleForm.username=?1")
	Page<ChangeForm> findByUsername(String username,Pageable pageable);
}
