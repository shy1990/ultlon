package com.sj1688.ultlon.domain.dto;

import java.util.function.Function;

import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.RefundForm;
import com.sj1688.ultlon.domain.TaskForm;

public class AppRefundFormDtoFunction implements Function<RefundForm, AppRefundFormDto>{

	@Override
	public AppRefundFormDto apply(RefundForm t) {
		AppRefundFormDto dto=new AppRefundFormDto();
		dto.setId(t.getTaskForm().getId());
		dto.setRemark(t.getTaskForm().getRemark());
		dto.setRealRefundMoney(t.getRealRefundMoney());
		dto.setOrderPrice(t.getOrderPrice());
		dto.setCurrentPrice(t.getCurrentPrice());
		try{
			AfterSaleForm asf=t.getTaskForm().getAfterSaleForm();
			dto.setGoodsName(asf.getGoodsName());
		    dto.setImei(asf.getImei());
		    dto.setUsername(asf.getUsername());
		    dto.setOrderNum(asf.getOrderNum());

		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

}
