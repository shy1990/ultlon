package com.sj1688.ultlon.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.ChangeFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.ChangeForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.ChangeFormCreateEvent;
import com.sj1688.ultlon.event.ChangeFormUpdateEvent;
import com.sj1688.ultlon.service.ChangeService;
@Service
public class ChangeServiceImpl implements ChangeService{
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private ChangeFormRepository rfr;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public ChangeForm genrateChangeForm(TaskForm taskForm) {
		ChangeForm rf=new ChangeForm(taskForm);
		return rf;
	}

	@Override
	public void save(ChangeForm entity) {
		ChangeForm genrateChangeForm = genrateChangeForm(entity.getTaskForm());
		genrateChangeForm.setRemark(entity.getRemark());
		ChangeForm save = rfr.save(genrateChangeForm);
		ctx.publishEvent(new ChangeFormCreateEvent(save));
	}

	@Override
	public Page<ChangeForm> get(Pageable page) {
		return rfr.findAll(page);
	}

	@Override
	public void updateStatus(ChangeForm entity,FormAuditStatus status,String remark) {
		Boolean processed = entity.getStatus().equals(
				FormAuditStatus.AGREE)|| entity.getStatus().equals(
						FormAuditStatus.REJECT);
		if(!processed){
			ChangeForm old=new ChangeForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(status);
			if(remark != null && !"".equals(remark)){
				entity.setRemark(remark);
			}
			ChangeForm save = rfr.save(entity);
			ctx.publishEvent(new ChangeFormUpdateEvent(save,old));
		}
	}

//	@Override
//	public Page<ChangeForm> findAll(String imei,Pageable pageable) {
//		return rfr.findAll(pageable);
//	}
	
	@Override
	public Page<ChangeForm> findAll(String imei,Pageable pageable) {
		if(imei != null && !"".equals(imei)){
			return rfr.findByImei(imei,pageable);
		}else{
			return rfr.findAll(pageable);
		}
	}

	@Override
	public Page<ChangeForm> findAll2(String username, Pageable pageable) {
		if(username != null && !"".equals(username)){
			return rfr.findByUsername(username,pageable);
		}else{
			return rfr.findAll(pageable);
		}
	}

}
