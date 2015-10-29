package com.sj1688.ultlon.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.sj1688.ultlon.dao.mysql.FinanceFormRepository;
import com.sj1688.ultlon.dao.mysql.RefundFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.FinanceFormUpdateEvent;
import com.sj1688.ultlon.event.RefundFormCreateEvent;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.FinanceService;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.util.HttpClientUtils;

@Service
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private FinanceFormRepository ffr;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public void update(FinanceForm entity) {
		 ffr.save(entity);
	}

	@Override
	public Page<FinanceForm> get(Pageable page) {
		return ffr.findAll(page);
	}

	@Override
	public void updateStatus(FinanceForm entity, FormAuditStatus status,String remark,BigDecimal cost) {
		Boolean processed = entity.getStatus().equals(
				FormAuditStatus.AGREE)|| entity.getStatus().equals(
						FormAuditStatus.REJECT);
		if(!processed){
			FinanceForm old = new FinanceForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(status);
			if(remark != null && !"".equals(remark)){
				entity.setRemark(remark);
			}
			if(cost != null && !"".equals(cost)){
				entity.setRealPrice(cost);
			}
			FinanceForm save = ffr.save(entity);
			addTrading(save);
			ctx.publishEvent(new FinanceFormUpdateEvent(save, old));
		}
	}
	
	public void addTrading(FinanceForm ff){
		try {

			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", "REFUND");//退款
			param.put("description", ff.getRemark());
			param.put("amount", ff.getCurrentPrice());//现在的价格
			param.put("url", "http://www.3j1688.com/xxx");
			//param.put("payPassword", payPwd);

			/*List messageConverters = new ArrayList();
			messageConverters.add(new SourceHttpMessageConverter());
			messageConverters.add(new FormHttpMessageConverter());
			messageConverters.add(new MappingJacksonHttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);*/

			HttpHeaders httpHeaders = new HttpHeaders(); // 设置HTTP请求的请求头信息
			// 设置相应内容，相应内容将被转换为json格式返回
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			// 设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(param, httpHeaders);

			ResponseEntity<JSONObject> obj = restTemplate.postForEntity("http://192.168.2.247:58080/v2/tradings/", httpEntity, JSONObject.class);

			JSONObject o = obj.getBody();
			

		} catch (HttpServerErrorException e) {
			System.out.println("异常：" + e.getStatusCode() + "   ::  " + e.getStatusText());
			System.out.println("请求错误" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Page<FinanceForm> findAll(Pageable pageable) {
		return ffr.findAll(pageable);
	}

	@Override
	public void updatePoint(String username,BigDecimal orderPrice) {
		// TODO Auto-generated method stub
		 int point=b2bDao.findPointByUsername(username);
		 BigDecimal pt=orderPrice.divide(new BigDecimal(10), 1,java.math.RoundingMode.DOWN);
		int op=point-pt.intValue();
		b2bDao.updatePoint(username,op);
	}

	

	/*@Override
	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
