package com.sj1688.ultlon.controller.app;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.dto.AppRefundFormDto;
import com.sj1688.ultlon.domain.dto.AppRefundFormDtoFunction;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.service.TaskService;


/**
 * 退货单控制器 <br>
 */
@RestController
@RequestMapping("/app/refund")
public class AppRefundFormController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private RefundService refundService;	
	@Autowired
	private TaskFormRepository taskFormRepository;
	private AppRefundFormDtoFunction dtoFunction=new AppRefundFormDtoFunction();
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject add(@RequestParam(value = "taskId") TaskForm taskForm,RefundForm refundForm) {
		
//		refundForm.setTaskForm(taskForm);
//		String orderNum =taskForm.getAfterSaleForm().getOrderNum();
//		String mobile = taskService.findMobileByOrderNum1(orderNum);
//		System.out.println(mobile);
		JSONObject result=new JSONObject();
        try{
        	refundForm.setTaskForm(taskForm);
    		String orderNum =taskForm.getAfterSaleForm().getOrderNum();
    		String mobile = taskService.findMobileByOrderNum1(orderNum);
    		refundService.save(refundForm);
    		result.put("success", true);
        }catch(Exception e){
        	result.put("msg", "系统异常,请稍后重试");
        	result.put("success",false);
        }
		//refundService.save(refundForm);
		//return "refund/success";	
        return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(@RequestParam(value = "taskId")long taskId) {
		JSONObject result=new JSONObject();
        try{
			TaskForm taskForm = taskFormRepository.findOne(taskId);
			RefundForm rfForm=refundService.genrateRefundForm(taskForm);
			AppRefundFormDto dto=dtoFunction.apply(rfForm);
			result.put("data", dto);
			result.put("success", true);
			} catch (Exception e) {
				result.put("msg", "系统异常,请稍后重试");
				result.put("success", false);
				e.printStackTrace();
			}
			return result;
	}

}
