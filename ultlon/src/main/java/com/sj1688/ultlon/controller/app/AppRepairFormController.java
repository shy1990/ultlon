package com.sj1688.ultlon.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.dto.AppRefundFormDto;
import com.sj1688.ultlon.domain.dto.AppRepairFormDto;
import com.sj1688.ultlon.domain.dto.AppRepairFormDtoFunction;
import com.sj1688.ultlon.service.RepairService;
import com.sj1688.ultlon.service.TaskService;

/**
 * 维修单控制器 <br>
 */
@RestController
@RequestMapping("/app/repair")
public class AppRepairFormController {
	@Autowired
	private RepairService repairService;
	@Autowired
	private TaskFormRepository taskFormRepository;
	@Autowired
	private TaskService taskService;
	private AppRepairFormDtoFunction dtoFunction=new AppRepairFormDtoFunction();

	@RequestMapping(method = RequestMethod.POST)
	public JSONObject add(@RequestParam(value = "taskId") TaskForm taskForm,
			RepairForm repairForm,String remark) {
		JSONObject result=new JSONObject();
		try{
			repairForm.setTaskForm(taskForm);
			repairForm.setRemark(remark);
			repairService.save(repairForm);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
			e.printStackTrace();
		}
		
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(@RequestParam(value = "taskId") long taskId) {			
		JSONObject result=new JSONObject();
        try{
			TaskForm taskForm = taskFormRepository.findOne(taskId);
			RepairForm rpForm=repairService.genrateRepairForm(taskForm);
			AppRepairFormDto dto=dtoFunction.apply(rpForm);
			result.put("data", dto);
			result.put("success", true);
			} catch (Exception e) {
				result.put("msg", "系统异常,请稍后重试");
				result.put("success", false);
				e.printStackTrace();
			}
			return result;
	}

	@RequestMapping(value = "/save")
	public void save(String nameone, HttpServletRequest request) {
		System.out.println("已经进来了");
	}
	
	
}
