package com.sj1688.ultlon.service.impl;

import java.math.BigDecimal;
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
import com.sj1688.ultlon.event.RefundFormCreateEvent;
import com.sj1688.ultlon.event.RefundFormUpdateEvent;
import com.sj1688.ultlon.service.RefundService;
@Service
public class RefundServiceImpl implements RefundService{
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private RefundFormRepository rfr;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public RefundForm genrateRefundForm(AfterSaleForm afterSaleForm) {
		RefundForm rf=null;
		Map<String,BigDecimal> prices= b2bDao.findOrderPirceAndCurrentPrice(afterSaleForm.getSkuCode(),afterSaleForm.getOrderNum());
		rf=new RefundForm(afterSaleForm, prices.get("ORDER_PRICE"), prices.get("CURRENT_PRICE"));
		return rf;
	}

	@Override
	public void save(RefundForm entity) {
		RefundForm genrateRefundForm = genrateRefundForm(entity.getAfterForm());
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

}
