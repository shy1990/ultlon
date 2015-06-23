package com.sj1688.ultlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.ChangeService;

/**
 * 换货单控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/change</td>
 * <td>新增换货单</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/change</td>
 * <td>获取列表,?page=1&size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/change/{userId}/{imei}</td>
 * <td>查看能销售的服务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/change/edit</td>
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
@RequestMapping("/change")
public class ChangeFormController {
	@Autowired
	private ChangeService changeService;

	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestParam(value = "taskId") TaskForm taskForm,ChangeForm changeForm) {
		changeForm.setTaskForm(taskForm);
		changeService.save(changeForm);
		return "change/success";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "taskId") TaskForm taskForm,Model model) {
		ChangeForm genrateRefundForm = changeService.genrateChangeForm(taskForm);
		model.addAttribute("changeForm", genrateRefundForm);
		model.addAttribute("taskForm", taskForm);
		return "change/edit";
	}

}
