package com.sj1688.ultlon.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.RepairService;

/**
 * 维修单控制器 <br>
 */
@RestController
@RequestMapping("/app/repair")
public class AppRepairFormController {
	@Autowired
	private RepairService repairService;

	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestParam(value = "taskId") TaskForm taskForm,
			RepairForm repairForm) {
		repairForm.setTaskForm(taskForm);
		repairService.save(repairForm);
		return "repair/success";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "taskId") TaskForm taskForm,
			Model model) {
		RepairForm genrateRepairForm = repairService
				.genrateRepairForm(taskForm);
		model.addAttribute("repairForm", genrateRepairForm);
		model.addAttribute("taskForm", taskForm);
		return "repair/edit";
	}

	@RequestMapping(value = "/save")
	public void save(String nameone, HttpServletRequest request) {
		System.out.println("已经进来了");
	}
	
	
}
