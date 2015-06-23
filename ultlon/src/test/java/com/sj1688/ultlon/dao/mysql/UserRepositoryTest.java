package com.sj1688.ultlon.dao.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.sj1688.ultlon.UltlonApplication;
import com.sj1688.ultlon.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UltlonApplication.class)
@TransactionConfiguration(defaultRollback=false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository ur;
	@Test
	public void saveTest(){
		User user=new User();
		user.setPassword("123456");
		user.setRegions("111");
		user.setUsername("李四");
		ur.save(user);
	}
}
