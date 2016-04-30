package com.sj1688.ultlon.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.event.TaskFormUpdateEvent;
import com.sj1688.ultlon.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private TaskFormRepository tfr;
	@Autowired
	private ApplicationContext ctx;

	@Override
	public TaskForm genrateForm(AfterSaleForm afterSaleForm) {
		//TODO 这里要改成从业务数据库查询用户所属区域编码//首先从b2b数据库查出该用户名的id然后再从业务数据库获取用户所属区域编码
		String area = b2bDao.findUserArea(afterSaleForm.getUsername()).get(0);
		TaskForm result = new TaskForm(afterSaleForm, area);
		return result;
	}

	@Override
	public void save(TaskForm entity) {
		TaskForm genrateRefundForm = genrateForm(entity.getAfterSaleForm());
		tfr.save(genrateRefundForm);
	}

	@Override
	public Page<TaskForm> getNoProccessTask(Pageable pageable, List<String> areas) {
		return tfr.findByAreaInAndStatus(areas, pageable,FormAuditStatus.NOPROCESS );
	}

	@Override
	public void updateStatus(TaskForm entity,FormAuditStatus statusToUpdate) {
		/*Boolean isNoProcessed = entity.getStatus().equals(
				FormAuditStatus.NOPROCESS);*/
		//if(!isNoProcessed){
			TaskForm old=new TaskForm();
			try {
				BeanUtils.copyProperties(old, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			entity.setStatus(statusToUpdate);
			System.out.println("TaskServiceImpl:::::::::::updateStatus");
			TaskForm save = tfr.save(entity);
			ctx.publishEvent(new TaskFormUpdateEvent(save,old));
		//}
	}

	@Override
	public List<Map<String, Serializable>> findAllByOrderNum(String orderNum,String skuCode) {
		return b2bDao.findAllByOrderNum(orderNum,skuCode);
	}

	@Override
	public Page<TaskForm> getHistory(Pageable pageable, List<String> regionList) {
		return tfr.findByAreaInAndStatusNot(regionList, pageable,FormAuditStatus.NOPROCESS );
	}

	@Override
	public List<Map<String, Serializable>> findMobileByOrderNum(String orderNum) {
		return b2bDao.findMobileByOrderNum(orderNum);
	}

	@Override
	public String findMobileByOrderNum1(String orderNum) {
		return b2bDao.findMobileByOrderNum1(orderNum);
	}

	@Override
	public Page<TaskForm> getStatus(Pageable pageable,
			List<String> regionList, String status) {
		// TODO Auto-generated method stub
		FormAuditStatus fstatus = null;
		if(status.equals("处理中")){
			 fstatus=FormAuditStatus.PROCESSING;
		}else if(status.equals("同意")){
			fstatus=FormAuditStatus.AGREE;
		}else if(status.equals("拒绝")){
			fstatus=FormAuditStatus.REJECT;
		}else if(status.equals("已完成")){
			fstatus=FormAuditStatus.FINISH;
		}else{
			return tfr.findByAreaInAndStatusNot(regionList, pageable,FormAuditStatus.NOPROCESS );
		}
		return tfr.findByAreaInAndStatusIn(regionList, pageable, fstatus);
		
	}




}
