package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sj1688.ultlon.UltlonApplication;
import com.sj1688.ultlon.dao.oracle.B2BDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UltlonApplication.class)
public class B2BDaoTest {
	 @Autowired
	    private B2BDao b2bDao;
	    @Test
	    public void selectSomething(){
	        List<Map<String, Serializable>> findSomthing = b2bDao.findSomthing();
	        for (Map<String, Serializable> map : findSomthing) {
	            System.out.println("-------------------");
	            for (String key: map.keySet()) {
	                System.out.println(key+"--"+map.get(key).toString());
	            }
	        }
	    }
	
}
