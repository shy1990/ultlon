package com.sj1688.ultlon.dao.mssql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.UltlonApplication;
import com.sj1688.ultlon.domain.sz.CktCodeOut;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UltlonApplication.class)
@TransactionConfiguration(defaultRollback=false)
public class UserRepositoryTest {
	
	@Autowired
	private CktCodeOutRepository dao;
	
	@Test
	public void saveTest(){
		CktCodeOut cco = dao.findOne("013148009919756");
		System.out.println(JSON.toJSONString(cco));
	}
}
