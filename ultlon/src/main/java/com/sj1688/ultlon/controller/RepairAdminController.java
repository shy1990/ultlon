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

import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.service.RepairService;
import com.sj1688.ultlon.service.TaskService;

/**
 * 维修单管理控制器 <br>
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
 * <td>新增维修单管理</td>
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
@RequestMapping("/admin/repair")
public class RepairAdminController {
	@Autowired
	private RepairService repairService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<RepairForm> assembler, Model model) {
		Page<RepairForm> repairForms = repairService.findAll(pageable);
		model.addAttribute("data", assembler.toResource(repairForms));
		return "admin/repair/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String cost(@RequestParam(value = "id")RepairForm repairForm,BigDecimal cost,String remark,FormAuditStatus status) {
		repairForm.setCost(cost);
		repairForm.setRemark(remark);
		repairForm.setStatus(status);
		repairService.update(repairForm);
		return "ok";
	}
	@Autowired
	private TaskService taskService;
	@RequestMapping(value = "/{repairId}/{status}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable(value = "repairId")RepairForm repairForm,@PathVariable(value = "status")String status,@RequestParam(value = "remark", required = false) String remark,String track_no) {
		repairService.updateStatus(repairForm,FormAuditStatus.valueOf(status),remark,track_no);
		String mobile = taskService.findMobileByOrderNum1(repairForm.getTaskForm().getAfterSaleForm().getOrderNum());
		System.out.println(mobile);
//			String msg = "审批已完成，请注意查看。。。。。。";
//		MsgUtil.sendMessage("mobile", msg, "SMS");
		return "ok";
	}
	
}
