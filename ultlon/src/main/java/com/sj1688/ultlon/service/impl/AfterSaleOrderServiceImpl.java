package com.sj1688.ultlon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON; 
import com.sj1688.ultlon.dao.mysql.AfterSaleOrderRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.service.AfterSaleOrderService;

@Service
public class AfterSaleOrderServiceImpl implements AfterSaleOrderService{
	@Autowired
	private AfterSaleOrderRepository or;
	
	@Autowired
	private B2BDao om;




	@Override
	public Map<String, Object> getOrder(String imei, String userId) {
		Map<String, Object> map = null;
		AfterSaleOrder uo = or.findByImei(imei);
		if(null!=uo && !uo.getEcerpNo().isEmpty()){
			map = om.selectByUidAndErp(userId, uo.getEcerpNo());
		}
		return map;
	}
	
	public Map<String, Object> judgeService(Date signForTime){
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		return map;
	}

	@Override
	public AfterSaleOrder save(AfterSaleOrder uo) {
		// TODO Auto-generated method stub
		return or.saveAndFlush(uo);
	}

	@Override
	public Map<String, Object> selectByUidAndErpAndGoodsId(String userId,
			String ecerpNo, String goodsId) {
		System.out.println(userId+"   "+ecerpNo+"   "+goodsId);
		return om.selectByUidAndErpAndGoodsId(userId, ecerpNo, goodsId);
	}

	@Override
	public Map<String, Object> getGoodsBySkunum(String skuNum) {
		// TODO Auto-generated method stub
		return om.getGoodsBySkunum(skuNum);
	}

	@Override
	public AfterSaleOrder findByImei(String imei) {
		// TODO Auto-generated method stub
		return or.findByImei(imei);
	}

	@Override
	public Map<String, Object> selectByArea(String ecerpNo, String goodsId) {
		// TODO Auto-generated method stub
		return om.selectByArea(ecerpNo, goodsId);
	}
	
}
