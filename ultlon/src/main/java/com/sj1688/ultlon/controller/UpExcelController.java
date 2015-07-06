package com.sj1688.ultlon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sj1688.ultlon.service.AfterSaleOrderService;
import com.sj1688.ultlon.service.GoodsService;
import com.sj1688.ultlon.util.ExcelReader;


@Controller
public class UpExcelController {
	
	@Autowired
	private AfterSaleOrderService os;
	
	@Autowired
	private GoodsService gs;
	
	@ResponseBody
	@RequestMapping(value = "/upload")
	public String upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		String result = "上传失败！";
		System.out.println(file.getOriginalFilename()+"   "+file.getOriginalFilename().indexOf(".xlsx"));
		if(file.getOriginalFilename().indexOf(".xlsx")<0){
			return "请上传后缀为xlsx格式的excel文件！";
		}
		int excelType = Integer.parseInt(request.getParameter("excelType"));
		try {
			switch(excelType){
				case 1:
					//上传出库记录
					ExcelReader.readOutStockExcel(file.getInputStream(),os,gs);
					break;
				case 2:
					//上传商品信息
					 ExcelReader.readGoodsExcel(file.getInputStream(), gs);
					break;
			}
			result = "上传成功！";
		} catch (Exception e) {
			System.out.println("上传文件异常："+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/excel")
	public String excel() {
		return "upExcel";
	}
	
}
