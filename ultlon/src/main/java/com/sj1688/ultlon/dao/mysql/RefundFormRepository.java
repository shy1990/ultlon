package com.sj1688.ultlon.dao.mysql;

import java.util.Date;

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
	
	@Query("select a from RefundForm a where a.taskForm.afterSaleForm.username=?1")
	Page<RefundForm> findByUsername(String username,Pageable pageable);
	
	@Query("select a from RefundForm a where a.taskForm.afterSaleForm.orderNum=?1")
	Page<RefundForm> findByOrderNum(String orderNum,Pageable pageable);
	
	@Query("select a from RefundForm a where a.lastModifiedDate between ?1 and ?2")
	Page<RefundForm> findByModifiedDateBetween(Date startDate,Date endDate,Pageable pageable);
}
