package com.sj1688.ultlon.dao.mssql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.sz.CktCodeOut;


public interface CktCodeOutRepository extends JpaRepository<CktCodeOut,String>{

	/**
	 * 根据串码id查找出库信息列表
	 * @param codeId
	 * @return
	 */
	List<CktCodeOut> findByCodeID(String codeId);
	
}
