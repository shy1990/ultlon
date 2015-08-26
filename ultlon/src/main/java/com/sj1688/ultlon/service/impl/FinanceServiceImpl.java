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
			ctx.publishEvent(new FinanceFormUpdateEvent(save, old));
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
