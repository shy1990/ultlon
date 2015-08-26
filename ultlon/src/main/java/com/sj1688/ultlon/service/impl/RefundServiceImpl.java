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
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.FinanceFormRepository;
import com.sj1688.ultlon.dao.mysql.RefundFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FinanceForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.RefundFormCreateEvent;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.util.HttpClientUtils;

@Service
public class RefundServiceImpl implements RefundService {
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private RefundFormRepository rfr;
	@Autowired
	private FinanceFormRepository ffr;
	@Autowired
	private ApplicationContext ctx;

	@Override
	public RefundForm genrateRefundForm(TaskForm taskForm) {
		RefundForm rf = null;
		AfterSaleForm afterForm = taskForm.getAfterSaleForm();
		Map<String, BigDecimal> prices = b2bDao.findOrderPirceAndCurrentPrice(
				afterForm.getSkuCode(), afterForm.getOrderNum());
		rf = new RefundForm(taskForm, prices.get("ORDER_PRICE"),
				prices.get("CURRENT_PRICE"));
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
	public FinanceForm genrateFinanceForm(TaskForm taskForm) {
		FinanceForm rf = null;
		AfterSaleForm afterForm = taskForm.getAfterSaleForm();
		Map<String, BigDecimal> prices = b2bDao.findOrderPirceAndCurrentPrice(
				afterForm.getSkuCode(), afterForm.getOrderNum());
		System.out.println(afterForm.getSkuCode()+","+afterForm.getOrderNum());
		rf = new FinanceForm(taskForm,prices.get("CURRENT_PRICE"));
		return rf;
	}
	
	@Override
	public void save(FinanceForm entity) {
		FinanceForm genrateFinanceForm = genrateFinanceForm(entity.getRefundForm().getTaskForm());
		System.out.println(entity.getRefundForm().getTaskForm());
		genrateFinanceForm.setRemark(entity.getRemark());
		genrateFinanceForm.setOrderPrice(entity.getRefundForm().getOrderPrice());
		FinanceForm save = ffr.save(genrateFinanceForm);
//		ctx.publishEvent(new RefundFormCreateEvent(save));
	}
	
	@Override
	public void update(RefundForm entity) {
		 rfr.save(entity);
	}

	@Override
	public Page<RefundForm> get(Pageable page) {
		return rfr.findAll(page);
	}

	@Override
	public void updateStatus(RefundForm entity, FormAuditStatus status,String remark) {
		Boolean processed = entity.getStatus().equals(
				FormAuditStatus.AGREE)|| entity.getStatus().equals(
						FormAuditStatus.REJECT);
		if(!processed){
			RefundForm old = new RefundForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(status);
			if(remark != null && !"".equals(remark)){
				entity.setRemark(remark);
			}
			RefundForm save = rfr.save(entity);
			ctx.publishEvent(new RefundFormUpdateEvent(save, old));
		}
	}

	@Override
	public Page<RefundForm> findAll(Pageable pageable) {
		return rfr.findAll(pageable);
	}

	@Override
	public void refundMoney(RefundForm form) {
		String orderId = b2bDao.findOrderId(form.getTaskForm()
				.getAfterSaleForm().getOrderNum());
		BigDecimal realRefundMoney = form.getRealRefundMoney();
		if (!realRefundMoney.equals(BigDecimal.ZERO)) {
			doRefundMoney(orderId, realRefundMoney);
		}
	}

	private void doRefundMoney(String orderId, BigDecimal realRefundMoney) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderId", orderId);
		params.put("reFundAmt", String.valueOf(realRefundMoney));

		HttpClientUtils.sendPostSSLRequest(
				"http://www.3j1688.com/yeePay/toRefund.html", params);
		
	}

	@Override
	public RefundForm findByTaskForm(TaskForm tf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
