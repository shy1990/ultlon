package com.sj1688.ultlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.RefundService;

/**
 * 退货单控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/refund</td>
 * <td>新增退货单</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/refund</td>
 * <td>获取列表,?page=1&size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/refund/{userId}/{imei}</td>
 * <td>查看能销售的服务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/refund/edit</td>
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
@RequestMapping("/refund")
public class RefundFormController {
	@Autowired
	private RefundService refundService;

	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestParam(value = "taskId") TaskForm taskForm,RefundForm refundForm) {
		refundForm.setTaskForm(taskForm);
		refundService.save(refundForm);
		return "refund/success";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "taskId")TaskForm taskForm,
			Model model) {
		RefundForm genrateRefundForm = refundService.genrateRefundForm(taskForm);
		model.addAttribute("refundForm", genrateRefundForm);
		model.addAttribute("taskForm", taskForm);
		return "refund/edit";
	}

}
