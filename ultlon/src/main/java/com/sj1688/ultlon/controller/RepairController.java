package com.sj1688.ultlon.controller;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
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

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.dao.mysql.RepairFormRepository;
import com.sj1688.ultlon.domain.RepairForm;

/**
 * 维修单控制器 <br>
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
 * <td>新增维修单</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/repair</td>
 * <td>获取列表,?size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/repair/{id}</td>
 * <td>获取报价单</td>
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
@RequestMapping("/repair")
public class RepairController {
	@Autowired
	private RepairFormRepository repairFormRepository;

	// TODO id查询demo
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") RepairForm form,Model model) {
		model.addAttribute("data", form);
		return "repair/show";
	}

	// TODO 分页查询demo
	@RequestMapping(method = RequestMethod.GET)
	public String list(Pageable pageable,
			PagedResourcesAssembler<RepairForm> assembler, Model model) {
		Page<RepairForm> repairForms = repairFormRepository.findAll(pageable);
		model.addAttribute("data", assembler.toResource(repairForms));
		model.addAttribute("json",
				JSON.toJSONString(assembler.toResource(repairForms)));
		return "repair/list";
	}

	// TODO 保存demo
	@RequestMapping(method = RequestMethod.POST)
	public String add(RepairForm repairForm) {
		repairFormRepository.save(repairForm);
		return "redirect:/repair";
	}

	// TODO 修改demo
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(
			@RequestParam(required = false, value = "id") RepairForm repairForm,
			Model model) {
		model.addAttribute("form", repairForm);
		return "repair/edit";
	}

	// TODO 更新demo
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") RepairForm form, RepairForm newForm) {
		try {
			BeanUtils.copyProperties(form, newForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		repairFormRepository.save(form);
		return "redirect:/repair";
	}
}
