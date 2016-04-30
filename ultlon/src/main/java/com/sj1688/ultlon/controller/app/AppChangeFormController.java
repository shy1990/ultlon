package com.sj1688.ultlon.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.dto.AppChangeFormDto;
import com.sj1688.ultlon.domain.dto.AppChangeFormDtoFunction;
import com.sj1688.ultlon.service.ChangeService;

/**
 * 换货单控制器 <br>
 * 
 */
@RestController
@RequestMapping("/app/change")
public class AppChangeFormController {
	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private TaskFormRepository taskFormRepository;
	
    private AppChangeFormDtoFunction dtoFunction=new AppChangeFormDtoFunction();
//	@RequestMapping(method = RequestMethod.POST)
//	public String add(@RequestParam(value = "taskId") TaskForm taskForm,ChangeForm changeForm) {
//		changeForm.setTaskForm(taskForm);
//		changeService.save(changeForm);
//		return "change/success";
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(@RequestParam(value = "taskId") TaskForm taskForm,ChangeForm changeForm) {
		JSONObject result=new JSONObject();
		System.out.println(changeForm);
		try{
			changeForm.setTaskForm(taskForm);
			changeService.save(changeForm);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
			e.printStackTrace();
		}		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject edit(@RequestParam(value = "taskId")long taskId) {
		JSONObject result=new JSONObject();
		try {
			TaskForm taskForm = taskFormRepository.findOne(taskId);
			ChangeForm genrateChangeForm = changeService.genrateChangeForm(taskForm);
			AppChangeFormDto dto=dtoFunction.apply(genrateChangeForm);
			result.put("data", dto);
			result.put("success", true);
		} catch (Exception e) {
			result.put("msg", "系统异常,请稍后重试");
			result.put("success", false);
			e.printStackTrace();
		}
		return result;	
//		model.addAttribute("changeForm", genrateRefundForm);
//		model.addAttribute("taskForm", taskForm);
//		return "change/edit";
	}

}
