package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.UltlonApplication;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.service.AfterSaleOrderService;
import com.sj1688.ultlon.service.AfterSaleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UltlonApplication.class)
public class B2BDaoTest {
	 @Autowired
	    private B2BDao b2bDao;
	 
	 @Autowired
	 private AfterSaleService ass;
	 
	 @Autowired
	 private AfterSaleOrderService asos;
	 
	 @Test
	 public void test(){
		 //ass.genrateAfterSaleForm("xq0004","d78de2dfeca94decae759fc8c91b85c3");
		 asos.getOrder("xq0004","d78de2dfeca94decae759fc8c91b85c3");
	 }
	 
	    public void selectSomething(){
	        List<Map<String, Serializable>> findSomthing = b2bDao.findSomthing("宋");
	        for (Map<String, Serializable> map : findSomthing) {
	            System.out.println("-------------------");
	            for (String key: map.keySet()) {
	                System.out.println(key+"--"+map.get(key).toString());
	            }
	        }
	    }
	    
	    public void selectSomething1(){
	    	List<Map<String, Serializable>> aa = b2bDao.findMobileByOrderNum("20150123161739495");
	    	System.out.println("+++++++++++++++=");
	    	System.out.println(JSON.toJSONString(aa));
	    }
	    
	    @Test
	    public void findNRGBy(){
//	    	Map<String, Serializable> aa = b2bDao.findNRGBy("10513602","DD11111111");
//	    	System.out.println("+++++++++++++++=");
//	    	System.out.println(JSON.toJSONString(aa));
//	    	
//	    	 aa = b2bDao.findNRGBy("10509802","DD02913368");
//	    	System.out.println("+++++++++++++++=");
//	    	System.out.println(JSON.toJSONString(aa));	
	    	
	    	List<Map<String, Object>> sh=b2bDao.findSzImei("1111");
	    	if(sh.size()==0){
	    		sh=b2bDao.findGyImei("860128032747857");
	    		System.out.println("guanyi"+sh);
	    	}
	    	for(int i=0;i<sh.size();i++){
	    	System.out.println("=============="+sh.get(i)+"=============");	    	
	    	}
//	    	for(Object i:sh){
//	    		System.out.println(i);
//	    	}
	    	
	    }
	
}
