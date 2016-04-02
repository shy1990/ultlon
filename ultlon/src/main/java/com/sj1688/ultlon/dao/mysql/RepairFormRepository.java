package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;


public interface RepairFormRepository extends JpaRepository<RepairForm, Long> {
	RepairForm findByTaskForm(TaskForm tf);
	@Query("select a from RepairForm a where a.taskForm.afterSaleForm.imei=?1")
	Page<RepairForm> findByImei(String imei, Pageable pageable);
}
