package com.sj1688.ultlon.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;
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
	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestParam(value = "taskId") TaskForm taskForm,RefundForm refundForm) {
		refundForm.setTaskForm(taskForm);
		String orderNum =taskForm.getAfterSaleForm().getOrderNum();
		String mobile = taskService.findMobileByOrderNum1(orderNum);
		System.out.println(mobile);
//			String msg = "您的申请已通过审批，正在打款请稍候。。。。。。";
//		MsgUtil.sendMessage("mobile", msg, "SMS");
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
