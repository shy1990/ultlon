package com.sj1688.ultlon.domain.dto;

import java.util.function.Function;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.RepairForm;

public class AppRepairFormDtoFunction implements Function<RepairForm, AppRepairFormDto> {

	@Override
	public AppRepairFormDto apply(RepairForm t) {
		AppRepairFormDto dto=new AppRepairFormDto();
		dto.setId(t.getTaskForm().getId());
		dto.setStatus(t.getTaskForm().getStatus().toString());
		dto.setRemark(t.getTaskForm().getRemark());
		dto.setCreatedDate(t.getTaskForm().getCreatedDate().toDate());
		//dto.setTrack_no(t.getTrack_no());
		try{
			AfterSaleForm asf=t.getTaskForm().getAfterSaleForm();
			dto.setGoodsName(asf.getGoodsName());
			dto.setImei(asf.getImei());
		    dto.setUsername(asf.getUsername());
		    dto.setCost(t.getCost());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	
}
