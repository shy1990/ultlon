package com.sj1688.ultlon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sj1688.ultlon.dao.mssql.CktCodeOutRepository;
import com.sj1688.ultlon.dao.mysql.AfterSaleOrderRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.domain.sz.CktCodeOut;
import com.sj1688.ultlon.service.AfterSaleOrderService;

@Service
public class AfterSaleOrderServiceImpl implements AfterSaleOrderService{
	@Autowired
	private AfterSaleOrderRepository or;
	
	@Autowired
	private B2BDao om;
	
	@Autowired
	private CktCodeOutRepository ccor;




	@Override
	public Map<String, Object> getOrder(String imei, String userId) {
		Map<String, Object> map = null;
		List<CktCodeOut> ccos = ccor.findByCodeID(imei);
		List<AfterSaleOrder> ors = new ArrayList<AfterSaleOrder>();
		for(CktCodeOut cco:ccos){
			String[] config = cco.getConfig().split(":");
			if(config.length>0){
				AfterSaleOrder a = new AfterSaleOrder();
				a.setEcerpNo(cco.getRemark());
				a.setBarCode(config[2]);
				a.setImei(cco.getCodeID());
				ors.add(a);
			}
		}
		
		System.out.println("神州串码数据："+JSON.toJSONString(ors));
		
		if(ors.size()==0){//没有从神州查到数据，从串码数据库查询
			ors = or.findByImeiOrderByCreatedDateDesc(imei);
		}
		
		System.out.println("神州串码数据2："+JSON.toJSONString(ors));
		
		AfterSaleOrder uo = ors!=null&&ors.size()>0?ors.get(0):null;
		System.out.println("查出的串码信息："+JSON.toJSONString(uo));
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
		List<AfterSaleOrder> ors = or.findByImeiOrderByCreatedDateDesc(imei);
		return ors!=null?ors.get(0):null;
	}

	@Override
	public Map<String, Object> selectByArea(String ecerpNo, String goodsId) {
		// TODO Auto-generated method stub
		return om.selectByArea(ecerpNo, goodsId);
	}
	
}
