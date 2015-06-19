package com.sj1688.ultlon.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.RepairFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.RepairForm;
import com.sj1688.ultlon.event.RepairFormCreateEvent;
import com.sj1688.ultlon.event.RepairFormUpdateEvent;
import com.sj1688.ultlon.service.RepairService;
@Service
public class RepairServiceImpl implements RepairService{
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private RepairFormRepository rfr;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public RepairForm genrateRepairForm(AfterSaleForm afterSaleForm) {
		RepairForm rf=new RepairForm(afterSaleForm);
		return rf;
	}

	@Override
	public void save(RepairForm entity) {
		RepairForm genrateRepairForm = genrateRepairForm(entity.getAfterSaleForm());
		genrateRepairForm.setRemark(entity.getRemark());
		genrateRepairForm.setCost(entity.getCost());
		RepairForm save = rfr.save(genrateRepairForm);
		ctx.publishEvent(new RepairFormCreateEvent(save));
	}

	@Override
	public Page<RepairForm> get(Pageable page) {
		return rfr.findAll(page);
	}

	@Override
	public void updateStatus(RepairForm entity,FormAuditStatus status) {
		Boolean isNoProcessed=entity.getStatus().equals(FormAuditStatus.NOPROCESS);
		if(isNoProcessed){
			RepairForm old=new RepairForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(status);
			RepairForm save = rfr.save(entity);
			ctx.publishEvent(new RepairFormUpdateEvent(save,old));
		}
	}

	@Override
	public Page<RepairForm> findAll(Pageable pageable) {
		return rfr.findAll(pageable);
	}

}