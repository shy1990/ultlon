package com.sj1688.ultlon.controller.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.domain.dto.AppTaskFormDto;
import com.sj1688.ultlon.domain.dto.AppTaskFormDtoFunction;
import com.sj1688.ultlon.service.TaskService;

/**
 * 售后任务控制器 <br>
 * 
 */

@RestController
@RequestMapping("/app/task")
public class AppTaskController {
	@Autowired
	private TaskService taskService;
	private AppTaskFormDtoFunction dtoFunction = new AppTaskFormDtoFunction();

	@Autowired
	private TaskFormRepository taskFormRepository;
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") TaskForm form, Model model) {
		model.addAttribute("data", form);
		return "task/show";
	}	
	/**
	 * 获取当前业务员的售后申请列表
	 * @param pageable
	 * @param assembler
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	//@ResponseBody
	public Map<String,Object> list(Pageable pageable,PagedResourcesAssembler<TaskForm> assembler,@RequestParam("area")String area) {
		//TODO 这里从业务数据库查询该业务所负责的区域编码//或者如果android那边能传入该业务负责的区域id那也是刻意地
		//如果是app传入的是业务名称，那么先根据业务名称查找业务id然后根据id查找业务所负责的区域id列表
		//最好app能够直接传入区域id列表
		//由于区域编码不同所以以前的数据就看不到了
		//System.out.println(aa);
		//确定，app端传入业务员负责的区域id，String 以逗号隔开传入 
		//String area = "2278,2212,2211,2210,2209,2208,2207,2206,2205,2204,2203,2213,2202,2201,2200,2199,2198,2197,2196,2195,2194,3275,2193,2192,2191,2190,2189,2188,2187,2186,2185,2184,2183,2339,2338,2337,2336,2335,2334,2333,2332,2331,2330,2329,2328,2327,2326,2325,2324,3268,2323,2322,2321,2320,2319,2318,2317,2316,2315,2314,2313,2312,2311,2310,2309,2308,2307,2306,2305,2304,2303,2302,2301,2300,2275,2274,2273,2272,2271,2270,2269,2268,2267,2266,2265,2264,2263,2262,2261,2260,2259,2258,2257,2256,2255,2254,2253,2252,2251,2250,2249,2248,2247,2246,2245,2244,2243,2242,2241,2240,2239,2238,2237,2236,2235,2234,2233,2232,2231,2230,2229,2228,2227,2226,2225,2224,2223,2222,2221,2220,2219,2218,2217,2216,2215,2214,2182,2277,2276,2299,2298,2297,2296,2295,2294,2293,2292,2291,2290,2289,2288,2287,2286,2285,2284,2283,2282,2281,2280,2279";
		//User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			Sort sort = new Sort(Direction.DESC,"createdDate");
			List<String> areaList = Arrays.asList(area.split(","));
			Page<TaskForm> tasks = taskService.getNoProccessTask(new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort), areaList);
			List<AppTaskFormDto> dtoList = new ArrayList<AppTaskFormDto>();
			List<TaskForm> tasksContent = tasks.getContent();
			tasksContent.forEach(t->{
				dtoList.add(dtoFunction.apply(t));
			});

			result.put("data", dtoList);
			result.put("totalPages", tasks.getTotalPages());
			result.put("number", tasks.getNumber());//当前页	
			result.put("success", true);
		}catch(Exception e){
			result.put("msg", "系统异常,请稍后重试");
			result.put("success", false);
		}
		
		//result.put("HttpStatus", "200");
		//手机名称，申请人（用户）名称，申请时间，类型，id，状态，备注
		return result;
	}
	/**
	 * 申请处理历史
	 * @param pageable
	 * @param assembler
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/historyList", method = RequestMethod.GET)
	public Map<String,Object> historyList(Pageable pageable,
			PagedResourcesAssembler<TaskForm> assembler, @RequestParam("area")String area) {
		Sort sort=new Sort(Direction.DESC,"createdDate");
		Map<String, Object> result=new HashMap<String,Object>();
		try{
			List<String> areaList=Arrays.asList(area.split(","));
			Page<TaskForm> tasks = taskService.getHistory(new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),sort), areaList);
			List<AppTaskFormDto>dtoList=new ArrayList<AppTaskFormDto>();
			List<TaskForm> tasksContentForms=tasks.getContent();
			tasksContentForms.forEach(t->{
				dtoList.add(dtoFunction.apply(t));
			});
			result.put("data", dtoList);
			result.put("totalPages",tasks.getTotalPages());
			result.put("number", tasks.getNumber());
			result.put("success", true);
		}catch(Exception e){
			result.put("msg", "系统异常,请稍后重试");
			result.put("success", false);
		}	
		return result;
	}
	
	/**
	 * 拒绝申请
	 * @param form
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/{id}/reject", method = RequestMethod.POST)
	@ResponseBody
	public String reject(@PathVariable(value = "id") TaskForm form,@RequestParam("remark")String remark) {
		form.setRemark(remark);
		taskService.updateStatus(form,FormAuditStatus.REJECT);
		System.out.println(form.getStatus());
		return "ok";
	}

	/**
	 * 开箱损和30天退换货处理，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = { "/kxs", "/thh30" }, method = RequestMethod.GET)
//	public String edit(@RequestParam(value = "taskId") TaskForm taskForm,
//			Model model) {
//		model.addAttribute("taskForm", taskForm);
//		List<Map<String,Serializable>> map = taskService.findAllByOrderNum(taskForm.getAfterSaleForm().getOrderNum().trim(),taskForm.getAfterSaleForm().getSkuCode().trim());
//		//根据taskForm的信息去b2bDao查询相关信息
//		//然后把查出的相关信息返回到页面
//		System.out.println(JSON.toJSONString(map)); 
//		model.addAttribute("orderInfo", map.size()>0?map.get(0):null);
//		return "task/edit";
//	}  
	@RequestMapping(value = { "/kxs", "/thh30" }, method = RequestMethod.GET)
	public JSONObject edit(@RequestParam(value = "taskId") long taskId,
			Model model) {
		JSONObject result=new JSONObject();
		try{
			TaskForm taskForm = taskFormRepository.findOne(taskId);
			AppTaskFormDto dto=dtoFunction.apply(taskForm);
			List<Map<String,Serializable>> map = taskService.findAllByOrderNum(taskForm.getAfterSaleForm().getOrderNum().trim(),taskForm.getAfterSaleForm().getSkuCode().trim());
			result.put("data", dto);
			result.put("orderInfo", map.size()>0?map.get(0):null);
			System.out.println(result);
			result.put("success", true);
		}catch(Exception e){
			result.put("msg", "系统异常,请稍后重试");
			result.put("success", false);
			e.printStackTrace();
		}		
		return result;
	} 

	/**
	 * 多美达百日换新，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/dmdhx100" }, method = RequestMethod.GET)
	public String dmdhx100(@RequestParam(value = "taskId") TaskForm taskForm,
			Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/dmdhx100";
	}

	/**
	 * 维修，根据记录id获取记录详情
	 * @param taskForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/wx" }, method = RequestMethod.GET)
	public String wx(@RequestParam(value = "taskId") TaskForm taskForm, Model model) {
		model.addAttribute("taskForm", taskForm);
		return "task/wx";
	}
	
	/**
	 * 帮助中心
	 * @return
	 */
	@RequestMapping(value = "help", method = RequestMethod.GET)
	public String help() {
		
		return "task/sjzc";
	}
}
