package com.sj1688.ultlon.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.AfterSaleFormRepository;
import com.sj1688.ultlon.dao.mysql.AfterSaleOrderRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.domain.AfterSaleType;
import com.sj1688.ultlon.event.AfterSaleFormCreateEvent;
import com.sj1688.ultlon.service.AfterSaleService;
import com.sj1688.ultlon.service.exception.NotSuportException;
import com.sj1688.ultlon.util.ApplicationUtil;
import com.sj1688.ultlon.util.DateUtil;
@Service
public class AfterSaleServiceImpl implements AfterSaleService{
	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private B2BDao b2bdao;
	@Autowired
	private AfterSaleFormRepository asfRepository;
	@Autowired
	private AfterSaleOrderRepository asor;
	
	@Override
	public AfterSaleForm genrateAfterSaleForm(String imei, String userId) {
		AfterSaleForm asf=new AfterSaleForm(imei,userId);
		
		Map<String, String> orderMap=getOrderMap(imei);
		
		if(orderMap!=null){
			asf.setSkuCode(orderMap.get("skucode"));
			Map<String, Serializable> orderDetialMap=getOrderDetialMap(orderMap);
			asf.setOrderNum((String) orderDetialMap.get("ORDER_NUM"));
			asf.setReceiveTime((Date) orderDetialMap.get("RECEIVE_TIME"));
			asf.setGoodsName((String) orderDetialMap.get("GOODS_NAME"));
		}else{
			//不属于咱们商品 直接维修
			asf.setType(AfterSaleType.WX);
		}
		return asf;
	}
	/**
	 *根据dd号 去b2b查询订单
	 * @param orderMap
	 * @return ORDER_NUM  RECEIVE_TIME GOODS_NAME
	 */
	private Map<String, Serializable> getOrderDetialMap(
			Map<String, String> orderMap) {
		Map<String, Serializable> result=b2bdao.findNRGBy(orderMap.get("skucode"),orderMap.get("ddnum"));
		return result;
	}
	
	/**
	 * 根据串号查询商品编号，dd号
	 * @param imei
	 * @return skucode,ddnum
	 */
	private Map<String, String> getOrderMap(String imei) {
		Map<String, String> result=new HashMap<String, String>();
		//TODO 根据串号查询商品编号，dd号
		AfterSaleOrder aso = asor.findByImei(imei);
		result.put("skucode", aso.getNormsCode());
		result.put("ddnum", aso.getEcerpNo());
		return result;
	}

	@Override
	public void save(AfterSaleForm entity) {
		List<AfterSaleType> types = getTypes(entity.getReceiveTime(), entity.getGoodsName());
		if(types.contains(entity.getType())){
			AfterSaleForm save = asfRepository.save(entity);
			ctx.publishEvent(new AfterSaleFormCreateEvent(save));
		}else{
			throw new NotSuportException(entity.getImei()+"不能享受"+entity.getType()+"服务。");
		}
	}

	@Override
	public Page<AfterSaleForm> get(String userId, Pageable page) {
		return asfRepository.findByUsername(userId, page);
	}

	@Override
	public void update(AfterSaleForm entity) {
		asfRepository.save(entity);
	}
	
	@Override
	public List<AfterSaleType> getTypes(Date receiveTime, String goodsName) {
		// TODO 计算可享受服务 不准确！！！
		List<AfterSaleType> resultList=new ArrayList<AfterSaleType>();
		
		if(null==receiveTime){//没有签收那么可以享受所有服务
			//没有签收时间和签收时间为空。可以享受所有服务
			resultList.add(AfterSaleType.KXS);
			resultList.add(AfterSaleType.THH30);
			resultList.add(AfterSaleType.WX);
			if(goodsName.indexOf("多美达")>-1){
				resultList.add(AfterSaleType.DMDHX100);
			}
			return resultList;
		}else {
			long Poor = DateUtil.compareNowDate(receiveTime);
			if(ApplicationUtil.getKxs()>=Poor){//开箱损
				resultList.add(AfterSaleType.KXS);
			}
			if(ApplicationUtil.getThh30()>=Poor){//退换货
				resultList.add(AfterSaleType.THH30);
			}
			if(ApplicationUtil.getWx()>=Poor){//维修
				resultList.add(AfterSaleType.WX);
			}
			if(ApplicationUtil.getDmdhx100()>=Poor && goodsName.indexOf("多美达")>-1){
				resultList.add(AfterSaleType.DMDHX100);
			}
		}
		return resultList;
	}

}