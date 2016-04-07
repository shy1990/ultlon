package com.sj1688.ultlon.controller.app;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.service.TaskService;

/**
 * 售后任务控制器 <br>
 * 
 */

@RestController
@RequestMapping("/app/task")
public class AppTaskController {
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") TaskForm form, Model model) {
		model.addAttribute("data", form);
		return "task/show";
	}
	
	/**
	 * 获取当前业务员的售后申请列表
	 * @param pageable
	 * @param assembler
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<TaskForm> assembler, Model model) {
		//TODO 这里从业务数据库查询该业务所负责的区域编码//或者如果android那边能传入该业务负责的区域id那也是刻意地
		//如果是app传入的是业务名称，那么先根据业务名称查找业务id然后根据id查找业务所负责的区域id列表
		//最好app能够直接传入区域id列表
		//由于区域编码不同所以以前的数据就看不到了
		//确定，app端传入业务员负责的区域id，String 以逗号隔开传入 
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Page<TaskForm> tasks = taskService.getNoProccessTask(pageable, user.getRegionList());
		model.addAttribute("data", assembler.toResource(tasks));
		model.getClass().getName();

		return "task/list";
	}

	/**
	 * 申请处理历史
	 * @param pageable
	 * @param assembler
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/historyList", method = RequestMethod.GET)
	public String historyList(Pageable pageable,
			PagedResourcesAssembler<TaskForm> assembler, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Page<TaskForm> tasks = taskService.getHistory(pageable, user.getRegionList());
		model.addAttribute("data", assembler.toResource(tasks));

		return "task/history_list";
	}
	
	/**
	 * 拒绝申请
	 * @param form
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/{id}/reject", method = RequestMethod.POST)
	@ResponseBody
	public String reject(@PathVariable(value = "id") TaskForm form,@RequestParam("remark")String remark) {
		form.setRemark(remark);
		taskService.updateStatus(form,FormAuditStatus.REJECT);
		return "ok";
	}

	/**
	 * 开箱损和30天退换货处理，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
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

	/**
	 * 多美达百日换新，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/dmdhx100" }, method = RequestMethod.GET)
	public String dmdhx100(@RequestParam(value = "taskId") TaskForm taskForm,
			Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/dmdhx100";
	}

	/**
	 * 维修，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/wx" }, method = RequestMethod.GET)
	public String wx(@RequestParam(value = "taskId") TaskForm taskForm, Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/wx";
	}
	
	/**
	 * 帮助中心
	 * @return
	 */
	@RequestMapping(value = "help", method = RequestMethod.GET)
	public String help() {
		
		return "task/sjzc";
	}
}
