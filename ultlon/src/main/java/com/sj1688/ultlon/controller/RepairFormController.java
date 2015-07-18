package com.sj1688.ultlon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.RepairService;

/**
 * 维修单控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/repair</td>
 * <td>新增退货单</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/repair</td>
 * <td>获取列表,?page=1&size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/repair/{userId}/{imei}</td>
 * <td>查看能销售的服务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/repair/edit</td>
 * <td>修改页面</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * 
 * @author 武继明
 * 
 * 
 * 
 */
@Controller
@RequestMapping("/repair")
public class RepairFormController {
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
