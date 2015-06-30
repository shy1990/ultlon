package com.sj1688.ultlon.util;

import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.service.AfterSaleOrderService;



public class ReadOutExcelThread extends Thread{

	private AfterSaleOrderService os;
	private AfterSaleOrder uo ;
	
	public ReadOutExcelThread(AfterSaleOrderService os,AfterSaleOrder uo){
		this.os=os;
		this.uo=uo;
	}
	
	 @Override
	 public void run() {
		 try {
			 if(null!=uo){
				 os.save(uo);
			 }
			 System.gc();
		} catch (Exception e) {
			System.out.println("出库多线程异常："+e.getMessage());
		}
	 }
	
}
