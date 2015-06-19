package com.sj1688.ultlon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.TaskFormRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.FormAuditStatus;
import com.sj1688.ultlon.domain.TaskForm;
import com.sj1688.ultlon.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private B2BDao b2bDao;
	@Autowired
	private TaskFormRepository tfr;

	@Override
	public TaskForm genrateForm(AfterSaleForm afterSaleForm) {
		String area = b2bDao.findUserArea(afterSaleForm.getUsername());
		TaskForm result = new TaskForm(afterSaleForm, area);
		return result;
	}

	@Override
	public void save(TaskForm entity) {
		TaskForm genrateRefundForm = genrateForm(entity.getAfterSaleForm());
		tfr.save(genrateRefundForm);
	}

	@Override
	public Page<TaskForm> get(Pageable pageable, List<String> areas) {
		return tfr.findByAreaIn(areas, pageable);
	}

	@Override
	public void reject(TaskForm entity) {
		Boolean isNoProcessed = entity.getStatus().equals(
				FormAuditStatus.NOPROCESS);
		if (isNoProcessed) {
			entity.setStatus(FormAuditStatus.REJECT);
			tfr.save(entity);
		}
	}

}
