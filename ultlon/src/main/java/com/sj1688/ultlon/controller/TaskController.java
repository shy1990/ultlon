package com.sj1688.ultlon.controller;

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

import com.sj1688.ultlon.domain.AfterSaleForm;
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
		User user =(User) SecurityUtils.getSubject().getPrincipal();
		Page<TaskForm> tasks = taskService.get(pageable,user.getRegionList());
		model.addAttribute("data", assembler.toResource(tasks));
		
		return "task/list";
	}

	@RequestMapping(value ={"/edit"}, method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") AfterSaleForm afterSaleForm,
			Model model) {
		model.addAttribute("afterSaleForm", afterSaleForm);
		return "task/edit";
	}

}
