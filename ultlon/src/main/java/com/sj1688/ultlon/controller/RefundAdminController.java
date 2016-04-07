package com.sj1688.ultlon.controller;


import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.service.TaskService;

/**
 * 退货单管理控制器 <br>
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
 * <td>新增退货单管理</td>
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
@RequestMapping("/admin/refund")
public class RefundAdminController {
	@Autowired
	private RefundService refundService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<RefundForm> assembler, Model model,String imei) {
		
		Pattern pattern = Pattern.compile("[0-9]*"); 
		if(imei!=null){
			 Matcher match=pattern.matcher(imei);
		 System.out.println("ssss"+match.matches());
			if(match.matches()==false){
				Page<RefundForm> refundForms = refundService.findAll2(imei,pageable);
				System.out.println("imei"+imei);
				model.addAttribute("data", assembler.toResource(refundForms));
				model.addAttribute("meta", assembler.toResource(refundForms).getMetadata());
			}else{
				Page<RefundForm> refundForms=refundService.findAll(imei, pageable);
				System.out.println("imei"+imei);
				model.addAttribute("data",assembler.toResource(refundForms));
				model.addAttribute("meta",assembler.toResource(refundForms).getMetadata());
			}
		}else{
			Page<RefundForm> refundForms=refundService.findAll(imei, pageable);
			System.out.println("imei"+imei);
			model.addAttribute("data",assembler.toResource(refundForms));
			model.addAttribute("meta",assembler.toResource(refundForms).getMetadata());
		}

		return "admin/refund/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String status(@RequestParam(value = "id")RefundForm refundForm,String remark,FormAuditStatus status,FinanceForm financeForm) {
		
		financeForm.setRefundForm(refundForm);
		refundService.save(financeForm);
		
		refundForm.setRemark(remark);
		refundForm.setStatus(status);
		refundService.update(refundForm);
		
		return "ok";
	}
	
	@Autowired
	private TaskService taskService;
	@RequestMapping(value = "/{refundId}/{status}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable(value = "refundId")RefundForm refundForm,@PathVariable(value = "status")String status,@RequestParam(value = "remark", required = false) String remark) {
		refundService.updateStatus(refundForm,FormAuditStatus.valueOf(status),remark);
		String mobile = taskService.findMobileByOrderNum1(refundForm.getTaskForm().getAfterSaleForm().getOrderNum());
		System.out.println(mobile);
		System.out.println(refundForm.getRemark());
//			String msg = "审批已完成，请注意查看。。。。。。";
//		MsgUtil.sendMessage("mobile", msg, "SMS");
		return "ok";
	}

}
