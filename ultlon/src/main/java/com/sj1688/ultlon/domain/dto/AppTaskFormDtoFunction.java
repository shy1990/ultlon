package com.sj1688.ultlon.domain.dto;

import java.util.function.Function;

import org.apache.commons.beanutils.BeanUtils;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.TaskForm;

public class AppTaskFormDtoFunction implements Function<TaskForm, AppTaskFormDto>{

	@Override
	public AppTaskFormDto apply(TaskForm t) {
		AppTaskFormDto dto = new AppTaskFormDto();
		
		dto.setId(t.getId());
		dto.setStatus(t.getStatus().toString());
		dto.setCreatedDate(t.getCreatedDate().toDate());
		try {
			AfterSaleForm asf = t.getAfterSaleForm();
			dto.setImei(asf.getImei());
			dto.setOrderNum(asf.getOrderNum());
			dto.setGoodsName(asf.getGoodsName());
			dto.setType(asf.getType().toString());
			dto.setRemark(asf.getRemark());
			dto.setUsername(asf.getUsername());
			//BeanUtils.copyProperties(dto, asf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
