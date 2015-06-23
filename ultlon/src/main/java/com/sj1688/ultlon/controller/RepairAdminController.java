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
	public String cost(@RequestParam(value = "id")RepairForm repairForm,BigDecimal cost) {
		repairForm.setCost(cost);
		repairService.update(repairForm);
		return "redirect:admin/repair?sort=createdDate,desc";
	}

	@RequestMapping(value = "/{repairId}/{status}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable(value = "repairId")RepairForm repairForm,@PathVariable(value = "status")String status) {
		repairService.updateStatus(repairForm,FormAuditStatus.valueOf(status));
		return "ok";
	}

}
