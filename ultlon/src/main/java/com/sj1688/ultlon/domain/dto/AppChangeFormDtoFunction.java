package com.sj1688.ultlon.domain.dto;

import java.util.function.Function;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.ChangeForm;


public class AppChangeFormDtoFunction implements Function<ChangeForm, AppChangeFormDto>{

	@Override
	public AppChangeFormDto apply(ChangeForm t) {
		AppChangeFormDto dto=new AppChangeFormDto();
		dto.setId(t.getTaskForm().getId());
		try{
			AfterSaleForm asf=t.getTaskForm().getAfterSaleForm();
			dto.setGoodsName(asf.getGoodsName());
			dto.setRemark(asf.getRemark());
			dto.setUsername(asf.getUsername());
			dto.setImei(asf.getImei());
			dto.setOrderNum(asf.getOrderNum());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}
}
