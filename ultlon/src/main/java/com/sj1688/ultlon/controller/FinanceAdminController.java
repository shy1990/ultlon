package com.sj1688.ultlon.controller;

import java.math.BigDecimal;

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

import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.service.FinanceService;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.service.TaskService;

/**
 * 财审单管理控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/finance</td>
 * <td>新增退货单管理</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/finance</td>
 * <td>获取列表,?page=1&size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/refund/{userId}/{imei}</td>
 * <td>查看能销售的服务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/finance/edit</td>
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
@RequestMapping("/finance/refund")
public class FinanceAdminController {
	@Autowired
	private FinanceService financeService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<FinanceForm> assembler, Model model) {
		Page<FinanceForm> financeForms = financeService.findAll(pageable);
		model.addAttribute("data", assembler.toResource(financeForms));
		model.addAttribute("meta", assembler.toResource(financeForms).getMetadata());
		//System.out.println("+++++++++++++++++++++++++++++++++++++");
		//System.out.println(JSON.toJSONString(model));
		
		return "finance/refund/list";
	}
	
	
	@Autowired
	private TaskService taskService;
	@RequestMapping(value = "/{refundId}/{status}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable(value = "refundId")FinanceForm financeForm,@PathVariable(value = "status")String status,@RequestParam(value = "remark", required = false) String remark,BigDecimal cost) {
		financeService.updateStatus(financeForm,FormAuditStatus.valueOf(status),remark,cost);
//		String mobile = taskService.findMobileByOrderNum1(financeForm.getTaskForm().getAfterSaleForm().getOrderNum());
//		System.out.println(mobile);
		String username=financeForm.getTaskForm().getAfterSaleForm().getUsername();
		BigDecimal orderPrice=financeForm.getOrderPrice();
		if("AGREE".equals(status)){
			financeService.updatePoint(username,orderPrice);
		}
//		String msg = "审批已完成，请注意查看。。。。。。";
//		MsgUtil.sendMessage("mobile", msg, "SMS");
		return "ok";
	}

}
