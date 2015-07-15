package com.sj1688.ultlon.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.domain.Goods;
import com.sj1688.ultlon.service.AfterSaleOrderService;
import com.sj1688.ultlon.service.GoodsService;





public class ExcelReader {
	
	/**
	 * 读取出库excel文件
	 * @param file
	 * @return 
	 */
	public static String readOutStockExcel(InputStream file,AfterSaleOrderService os,GoodsService gs) throws Exception {
			OPCPackage opcPackage = OPCPackage.open(file);
			XSSFWorkbook xs = new XSSFWorkbook(opcPackage);  
	        XSSFSheet sheet = xs.getSheetAt(0);  
	       
	        int xsRows = sheet.getPhysicalNumberOfRows();  
	        ExecutorService pool = Executors.newFixedThreadPool(50);// 创建一个固定大小为3的线程池
	        for (int i = 1; i <= xsRows; i++) {  
	        	XSSFRow row = sheet.getRow(i);
	        	if(null!=row&&null!=row.getCell(2)&&!row.getCell(2).toString().equals("")&&!row.getCell(2).toString().equals("手机串码")){
	        	
	        		AfterSaleOrder o = new AfterSaleOrder();
	        		o.setImei(cellToStr(row.getCell(2)).toString());
	        		o.setBarCode(cellToStr(row.getCell(1)).toString());
	        		o.setEcerpNo(cellToStr(row.getCell(0)).toString());
	        		
	        		System.out.println("正在解析出库excel:"+xsRows+"   "+i);

		            pool.submit(new ReadOutExcelThread(os,o,gs));// 多线程保存
		    
	        	}
	        }  
            
	        pool.shutdown();
	        while (true) {
	            if (pool.isTerminated()) {// 地址转换完成
	            	break;
	            }
            }
	        System.gc(); 
		return "";
	}
	
	/**
	 * 读取出库excel文件
	 * @param file
	 * @return
	 */
	public static String readGoodsExcel(InputStream file,GoodsService gs) throws Exception {
		    //StringBuffer sql = new StringBuffer("INSERT INTO tb_order(bar_code,goods_code,`name`,short_name,norms_code,color,supplier,bar_code_type) VALUES");
			OPCPackage opcPackage = OPCPackage.open(file);
			XSSFWorkbook xs = new XSSFWorkbook(opcPackage);  
	        XSSFSheet sheet = xs.getSheetAt(0);  
	        int xsRows = sheet.getPhysicalNumberOfRows();  
	        for (int i = 1; i <= xsRows; i++) {  
	        	XSSFRow row = sheet.getRow(i);
	        	if(null!=row&&null!=row.getCell(0)&&!row.getCell(0).toString().equals("")&&!row.getCell(0).toString().equals("商品条码")){
	        	
	        		Goods g = new Goods();
	        		g.setBarCode(cellToStr(row.getCell(0)).toString());//条码
	        		g.setGoodsCode(cellToStr(row.getCell(1)).toString());//商品代码
	        		g.setName(cellToStr(row.getCell(2)).toString());//商品名称
	        		g.setShortName(cellToStr(row.getCell(3)).toString());
	        		g.setNormsCode(cellToStr(row.getCell(4)).toString());
	        		g.setColor(cellToStr(row.getCell(5)).toString());
	        		g.setSupplier(cellToStr(row.getCell(6)).toString());
	        		g.setBarCodeType(cellToStr(row.getCell(7)).toString());
	        		
	        		gs.save(g);
	        		
	        		System.out.println("正在解析出库excel:"+xsRows+"   "+i);
	        	}
	        }  
	        
	       // gs.executeSql(sql.toString());
	        
	        System.gc(); 
		return "";
	}
	
	
	/**
	 * cell转换成字符串，如果字符串是科学计数法，就转换以后再转换成字符串
	 * @param cell
	 * @return
	 */
	public static StringBuffer cellToStr(Cell cell){
		StringBuffer str = new StringBuffer();
		if(null!=cell){
			str = new StringBuffer(cell.toString()) ;
    		if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
        		DecimalFormat df = new DecimalFormat("0");
        		str = new StringBuffer(df.format(cell.getNumericCellValue()));
    		}
		}
		return str;
	}
	
	
}
