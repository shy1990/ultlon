package com.sj1688.ultlon.domain;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.common.base.Function;
import com.sj1688.ultlon.service.RefundService;
import com.sj1688.ultlon.service.RepairService;
import com.sj1688.ultlon.util.DateUtil;

public class AfterSaleFormDtoFunction  implements Function<AfterSaleForm, AfterSaleFormDTO>{

	
	public AfterSaleFormDtoFunction(RepairService rs,RefundService rss){
	}
	
	@Override
	public AfterSaleFormDTO apply(AfterSaleForm as) {
		AfterSaleFormDTO dto = new AfterSaleFormDTO();
		try {
			BeanUtils.copyProperties(dto, as);
			dto.setCreateTime(DateUtil.dateToStr(as.getCreatedDate().toDate()));
			dto.setReceiveTime(DateUtil.dateToStr(as.getReceiveTime()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.println("实体转换异常"+e.getMessage());
			e.printStackTrace();
		}
		return dto;
	}

}
