package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.TaskForm;

public interface FinanceFormRepository extends JpaRepository<FinanceForm, Long> {
	
}
