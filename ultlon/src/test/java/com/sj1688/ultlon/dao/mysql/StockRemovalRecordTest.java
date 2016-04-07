package com.sj1688.ultlon.dao.mysql;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sj1688.ultlon.UltlonApplication;
import com.sj1688.ultlon.domain.StockRemovalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=UltlonApplication.class)
public class StockRemovalRecordTest {

	@Autowired
	private StockRemovalRecordRepository dao;
	
	@Test
	public void add(){
		try {
			String filePath="/home/xq/文档/项目/售后改版/售后串码数据/B2B神州扫描记录4月1-7.xlsx";
			List<Map<Integer, String>> contentList = ExcelImport.readExcelContent(filePath);
			System.out.println(contentList.size());
			contentList.forEach((Map<Integer, String> excelContent)->{
				if(null!=excelContent){
					 excelContent.forEach((integer, s) -> { 
						 System.out.println(integer+"      "+s);
						 if(null!=s && s.indexOf("sku")<0){
							 String[] content = s.split("    ");
							 if(content.length>=2){
								 try {//增加个异常捕获，为了防止串号重复发生异常不向下执行
									 StockRemovalRecord srr = new StockRemovalRecord();
									 srr.setId(content[0]);
									 srr.setOrderNum(content[2]);
									 srr.setSkuCode(content[1]);
									 dao.saveAndFlush(srr);
									 srr=null;
								} catch (Exception e) {
								}
							 }
						 }
						 
				            if (integer > 0) {
				            	//System.out.println(s);
				                //String[] content = s.split("    ");
				            }
				        });
				}
			});
			
			
			//Map<Integer, String> excelContent = ;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常："+e.getMessage());
		}
	}
	
}
