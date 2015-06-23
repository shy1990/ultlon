package com.sj1688.ultlon.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.RefundFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.RefundFormCreateEvent;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.util.HttpClientUtils;
import com.sj1688.ultlon.util.Json;
import com.sj1688.ultlon.util.JsonUtil;
@Service
public class RefundServiceImpl implements RefundService{
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private RefundFormRepository rfr;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public RefundForm genrateRefundForm(TaskForm taskForm) {
		RefundForm rf=null;
		AfterSaleForm afterForm = taskForm.getAfterSaleForm();
		Map<String,BigDecimal> prices= b2bDao.findOrderPirceAndCurrentPrice(afterForm.getSkuCode(),afterForm.getOrderNum());
		rf=new RefundForm(taskForm, prices.get("ORDER_PRICE"), prices.get("CURRENT_PRICE"));
		return rf;
	}

	@Override
	public void save(RefundForm entity) {
		RefundForm genrateRefundForm = genrateRefundForm(entity.getTaskForm());
		genrateRefundForm.setRemark(entity.getRemark());
		RefundForm save = rfr.save(genrateRefundForm);
		ctx.publishEvent(new RefundFormCreateEvent(save));
	}

	@Override
	public Page<RefundForm> get(Pageable page) {
		return rfr.findAll(page);
	}

	@Override
	public void updateStatus(RefundForm entity,FormAuditStatus status) {
		Boolean isNoProcessed=entity.getStatus().equals(FormAuditStatus.NOPROCESS);
		if(isNoProcessed){
			RefundForm old=new RefundForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(status);
			RefundForm save = rfr.save(entity);
			ctx.publishEvent(new RefundFormUpdateEvent(save,old));
		}
	}

	@Override
	public Page<RefundForm> findAll(Pageable pageable) {
		return rfr.findAll(pageable);
	}

	@Override
	public void refundMoney(RefundForm form) {
		//TODO 查找订单的id
		String orderId = b2bDao.findOrderId(form.getTaskForm().getAfterSaleForm().getOrderNum());
		//TODO 
		//TODO 要退款的金额  ，退款
		BigDecimal realRefundMoney = form.getRealRefundMoney();
		if(!realRefundMoney.equals(BigDecimal.ZERO)){
			doRefundMoney(orderId,realRefundMoney);
		}
	}

	private void doRefundMoney(String orderId, BigDecimal realRefundMoney) {
		// TODO 宋宝真--->执行退款操作
		System.out.println("给订单:"+orderId+" 退款："+realRefundMoney);
		Map<String,String> params = new HashMap<String,String>();
		params.put("orderId",orderId);
		params.put("reFundAmt", String.valueOf(realRefundMoney));
		
		String s = HttpClientUtils.sendPostSSLRequest("http://www.3j1688.com/yeePay/toRefund.html", params);
		if(s != null && !"".equals(s)){
			Json json =(Json)JsonUtil.getObject4JsonString(s,Json.class);}
		
	}

}
