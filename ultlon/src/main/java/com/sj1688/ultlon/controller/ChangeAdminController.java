package com.sj1688.ultlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.service.ChangeService;

/**
 * 换货单管理控制器 <br>
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
 * <td>新增换货单管理</td>
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
@RequestMapping("/admin/change")
public class ChangeAdminController {
	@Autowired
	private ChangeService changeService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<ChangeForm> assembler, Model model) {
		Page<ChangeForm> changeForms = changeService.findAll(pageable);
		model.addAttribute("data", assembler.toResource(changeForms));
		return "admin/change/list";
	}

	@RequestMapping(value = "/{changeId}/{status}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable(value = "changeId")ChangeForm changeForm,@PathVariable(value = "status")String status) {
		changeService.updateStatus(changeForm,FormAuditStatus.valueOf(status));
		return "ok";
	}

}
