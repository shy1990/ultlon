package com.sj1688.ultlon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.AfterSaleFormDTO;
import com.sj1688.ultlon.domain.AfterSaleFormDtoFunction;
import com.sj1688.ultlon.domain.AfterSaleType;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.AfterSaleService;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.service.RepairService;
import com.sj1688.ultlon.service.exception.NotSuportException;
import com.sj1688.ultlon.util.DateUtil;

/**
 * 售后单控制器 <br>
 * <table>
 * <tbody>
 * <tr>
 * <th>方法</th>
 * <th>url</th>
 * <th>描述</th>
 * </tr>
 * <tr>
 * <td>POST</td>
 * <td>/aftersale</td>
 * <td>新增售后单</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/aftersale</td>
 * <td>获取列表,?page=1&size=2&sort=id,desc</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/aftersale/{userId}/{imei}</td>
 * <td>查看能销售的服务</td>
 * </tr>
 * <tr>
 * <td>GET</td>
 * <td>/aftersale/edit</td>
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
@RequestMapping("/aftersale")
public class AfterSaleFormController {
	@Autowired
	private AfterSaleService afterSaleService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private RepairService repairService;
	
	private AfterSaleFormDtoFunction asfdf = new AfterSaleFormDtoFunction(repairService,refundService);

	@RequestMapping(value="/{userId}",method = RequestMethod.GET)
	public String list(@PathVariable("userId")String userId,Pageable pageable,
			PagedResourcesAssembler<AfterSaleForm> assembler, Model model) {
		Page<AfterSaleForm> afterSaleForms = afterSaleService.get(userId, pageable);
		Map<String, Object> data = new HashMap<String, Object>();
		List<AfterSaleFormDTO> list = new ArrayList<AfterSaleFormDTO>();
		List<AfterSaleForm> afterSaleFormsList = afterSaleForms.getContent();
		for (AfterSaleForm as : afterSaleFormsList) {
			AfterSaleFormDTO dto = asfdf.apply(as);
			if(as!=null){
				TaskForm tf = new TaskForm(as,as.getId());
				if(as.getType().equals(AfterSaleType.WX)){
					RepairForm rf = repairService.findByTaskForm(tf);
					if(rf!=null){
						dto.setPrice(rf.getCost());
					}
				}
				if(as.getType().equals(AfterSaleType.THH30)){
					RefundForm refundForm = refundService.findByTaskForm(tf);
					if(refundForm!=null){
						dto.setPrice(refundForm.getCurrentPrice());
					}
				}
			}
			list.add(dto);
		}
		data.put("metadata", assembler.toResource(afterSaleForms).getMetadata());
		//model.addAttribute("data", assembler.toResource(AfterSaleForms));
		//model.addAttribute("json", JSON.toJSONString(assembler.toResource(afterSaleForms)));
		data.put("content", list);
		model.addAttribute("json", JSON.toJSONString(data));
		return "aftersale/list";
	}
	
	
	@RequestMapping(value = "/{userId}/{imei}", method = RequestMethod.GET)
	public String show(@PathVariable("userId") String userId,@PathVariable("imei") String imei,Model model) {
		AfterSaleForm genrateAfterSaleForm = afterSaleService.genrateAfterSaleForm(imei, userId);
		model.addAttribute("data", JSON.toJSON(genrateAfterSaleForm));
		return "aftersale/show";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET,params={"receiveTime","goodsName"})
	@ResponseBody
	public List<AfterSaleType> type(@PathVariable("userId") String userId,Long receiveTime,String goodsName,Model model) {
			Date parseDate = null;
			if(receiveTime!=null){
				parseDate = new Date(receiveTime);
			}
			return afterSaleService.getTypes(parseDate, goodsName);
	}

	@RequestMapping(value="testadd",method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody AfterSaleForm afterSaleForm) {
		try {
			afterSaleService.save(afterSaleForm);
		} catch (NotSuportException e) {
			return e.getMessage();
		}
		return "ok";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String add(AfterSaleForm afterSaleForm,@RequestParam("receiveTimeStr")String receiveTimeStr) {
		try {
			afterSaleForm.setReceiveTime(DateUtil.strToDate(receiveTimeStr));
			afterSaleService.save(afterSaleForm);
		} catch (NotSuportException e) {
			return e.getMessage();
		}
		return "您的售后申请已火速提交，请耐心等候....";
	}
}
