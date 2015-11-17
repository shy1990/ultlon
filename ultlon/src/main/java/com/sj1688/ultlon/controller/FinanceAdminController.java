package com.sj1688.ultlon.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.domain.AfterSaleForm;
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
	
	@RequestMapping(value = "/{refundId}", method = RequestMethod.POST)
	@ResponseBody
	public String agree(@PathVariable(value = "refundId")FinanceForm financeForm,@RequestParam(value = "remark", required = false) String remark,BigDecimal cost){
		if(addTrading(remark,cost,financeForm.getTaskForm().getAfterSaleForm().getUsername(),financeForm.getTaskForm().getAfterSaleForm().getOrderNum())){
			financeService.updateStatus(financeForm,FormAuditStatus.AGREE,remark,cost);
			String username=financeForm.getTaskForm().getAfterSaleForm().getUsername();
			BigDecimal orderPrice=financeForm.getOrderPrice();
			financeService.updatePoint(username,orderPrice);//更新用户积分
			return "ok";
		}else {
			return "err";
		}
	}
	
	/**
	 * 推送到新财务模块
	 * @param ff
	 */
	public boolean addTrading(String remark,BigDecimal price,String userName,String orderNum){
		boolean result = false;
		try {

			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", "REFUND");//退款
			param.put("description", remark);
			param.put("amount", price);//现在的价格
			param.put("url", "http://www.3j1688.com/ultlon/list/1.html");
			
			Map<String, String> order = financeService.findByOrderNum(orderNum);
			param.put("orderNum", orderNum);
			param.put("ecerpNo", order!=null&&order.containsKey("ECERP_NO")?order.get("ECERP_NO"):"");

			HttpHeaders httpHeaders = new HttpHeaders(); // 设置HTTP请求的请求头信息
			// 设置相应内容，相应内容将被转换为json格式返回
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			// 设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(param, httpHeaders);

			//String userName = ff.getTaskForm().getAfterSaleForm().getUsername();
			String url = "http://115.28.92.73:58080/v1/accounts/"+userName+ "/tradings/";
			System.out.println(url);
			ResponseEntity<JSONObject> obj = restTemplate.postForEntity(url, param, JSONObject.class);

			JSONObject o = obj.getBody();
			
			result = true;
		} catch (Exception e) {
			System.out.println("请求错误" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	

}
