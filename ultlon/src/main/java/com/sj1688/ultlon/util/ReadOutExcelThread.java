package com.sj1688.ultlon.util;

import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.domain.Goods;
import com.sj1688.ultlon.service.AfterSaleOrderService;
import com.sj1688.ultlon.service.GoodsService;



public class ReadOutExcelThread extends Thread{

	private AfterSaleOrderService os;
	private AfterSaleOrder uo ;
	private GoodsService gs;
	
	public ReadOutExcelThread(AfterSaleOrderService os,AfterSaleOrder uo,GoodsService gs){
		this.os=os;
		this.uo=uo;
		this.gs=gs;
	}
	
	 @Override
	 public void run() {
		 try {
			 if(null!=uo){
				Goods g = gs.findByBarCode(uo.getBarCode());
        		if(null!=g&& !g.getGoodsCode().isEmpty()){
        			uo.setNormsCode(g.getNormsCode());
        		}
				os.save(uo);
			 }
			 System.gc();
		} catch (Exception e) {
			System.out.println("出库多线程异常："+e.getMessage());
		}
	 }
	
}
