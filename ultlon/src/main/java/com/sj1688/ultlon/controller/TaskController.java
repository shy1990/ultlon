package com.sj1688.ultlon.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.service.TaskService;

/**
 * 售后任务控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/task</td>
 * <td>新增售后任务</td> 
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/task</td>
 * <td>获取列表,?size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/task/{id}</td>
 * <td>获取任务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/task/edit</td>
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
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") TaskForm form, Model model) {
		model.addAttribute("data", form);
		return "task/show";
	}
	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<TaskForm> assembler, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Page<TaskForm> tasks = taskService.getNoProccessTask(pageable, user.getRegionList());
		model.addAttribute("data", assembler.toResource(tasks));
		model.getClass().getName();

		return "task/list";
	}

	@RequestMapping(value="/historyList", method = RequestMethod.GET)
	public String historyList(Pageable pageable,
			PagedResourcesAssembler<TaskForm> assembler, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Page<TaskForm> tasks = taskService.getHistory(pageable, user.getRegionList());
		model.addAttribute("data", assembler.toResource(tasks));

		return "task/history_list";
	}
	
	@RequestMapping(value = "/{id}/reject", method = RequestMethod.POST)
	@ResponseBody
	public String reject(@PathVariable(value = "id") TaskForm form,@RequestParam("remark")String remark) {
		form.setRemark(remark);
		taskService.updateStatus(form,FormAuditStatus.REJECT);
		return "ok";
	}

	@RequestMapping(value = { "/kxs", "/thh30" }, method = RequestMethod.GET)
	public String edit(@RequestParam(value = "taskId") TaskForm taskForm,
			Model model) {
		model.addAttribute("taskForm", taskForm);
		List<Map<String,Serializable>> map = taskService.findAllByOrderNum(taskForm.getAfterSaleForm().getOrderNum().trim(),taskForm.getAfterSaleForm().getSkuCode().trim());
		//根据taskForm的信息去b2bDao查询相关信息
		//然后把查出的相关信息返回到页面
		System.out.println(JSON.toJSONString(map)); 
		model.addAttribute("orderInfo", map.size()>0?map.get(0):null);
		return "task/edit";
	}  

	@RequestMapping(value = { "/dmdhx100" }, method = RequestMethod.GET)
	public String dmdhx100(@RequestParam(value = "taskId") TaskForm taskForm,
			Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/dmdhx100";
	}

	@RequestMapping(value = { "/wx" }, method = RequestMethod.GET)
	public String wx(@RequestParam(value = "taskId") TaskForm taskForm, Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/wx";
	}
	@RequestMapping(value = "help", method = RequestMethod.GET)
	public String help() {
		
		return "task/sjzc";
	}
}
