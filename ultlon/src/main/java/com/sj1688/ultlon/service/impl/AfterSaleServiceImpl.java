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

import com.sj1688.ultlon.dao.mssql.CktCodeOutRepository;
import com.sj1688.ultlon.dao.mysql.AfterSaleFormRepository;
import com.sj1688.ultlon.dao.mysql.AfterSaleOrderRepository;
import com.sj1688.ultlon.dao.mysql.StockRemovalRecordRepository;
import com.sj1688.ultlon.dao.oracle.B2BDao;
import com.sj1688.ultlon.domain.AfterSaleForm;
import com.sj1688.ultlon.domain.AfterSaleOrder;
import com.sj1688.ultlon.domain.AfterSaleType;
import com.sj1688.ultlon.domain.StockRemovalRecord;
import com.sj1688.ultlon.domain.sz.CktCodeOut;
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
	//@Autowired
	//private AfterSaleOrderRepository asor;
	//@Autowired
	//private CktCodeOutRepository ccor;
	
	@Autowired
	private StockRemovalRecordRepository srrr;
	
	@Override
	public AfterSaleForm genrateAfterSaleForm(String imei, String userId) {
		AfterSaleForm asf=new AfterSaleForm(imei,userId);
		
		Map<String, String> orderMap=getOrderMap(imei);
		
		if(orderMap!=null){//
			asf.setSkuCode(orderMap.get("skucode"));
			Map<String, Serializable> orderDetialMap=getOrderDetialMap(orderMap);
			if(orderDetialMap!=null){
				asf.setOrderNum((String) orderDetialMap.get("ORDER_NUM"));
				asf.setReceiveTime(orderDetialMap.containsKey("RECEIVE_TIME")&&orderDetialMap.get("RECEIVE_TIME")!=null?DateUtil.strToDate(orderDetialMap.get("RECEIVE_TIME").toString()):null);
				asf.setGoodsName((String) orderDetialMap.get("GOODS_NAME"));
				//if(userId.equals("0")){//传入的用户id是0那么就是400代申请的
					asf.setUsername((String) orderDetialMap.get("MEMBER_ID"));
				//}
			}
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
		System.out.println("查询前："+orderMap);
		Map<String, Serializable> result = b2bdao.findNRGBy(orderMap.get("skucode").trim(),orderMap.get("ddnum").trim());
		System.out.println("查询结果："+result);
		return result;
	}
	
	/**
	 * 根据串号查询商品编号，dd号
	 * @param imei
	 * @return skucode,ddnum
	 */
	private Map<String, String> getOrderMap(String imei) {
		Map<String, String> result=new HashMap<String, String>();
		//List<AfterSaleOrder> ors = asor.findByImeiOrderByCreatedDateDesc(imei);
		/*List<CktCodeOut> ccos = ccor.findByCodeID(imei);
		List<AfterSaleOrder> ors = new ArrayList<AfterSaleOrder>();
		for(CktCodeOut cco:ccos){
			String str=cco.getConfig();
			if(str.contains("：")){
				str=str.replaceAll("：", ":");
			}
			String[] config = str.split(":");
			System.out.println(cco.getConfig()+"   "+config.length);
			if(config.length>2){
				AfterSaleOrder a = new AfterSaleOrder();
				a.setEcerpNo(cco.getRemark());
				a.setBarCode(config[2]);
				a.setNormsCode(config[2]);
				a.setImei(cco.getCodeID());
				ors.add(a);
			}
		}
		
		//System.out.println("神州串码数据："+JSON.toJSONString(ors));
		
		if(ors.size()==0){//没有从神州查到数据，从串码数据库查询
			ors = asor.findByImeiOrderByCreatedDateDesc(imei);
		}
		
		//System.out.println("神州串码数据2："+JSON.toJSONString(ors));
		
		
		AfterSaleOrder aso = ors!=null&&ors.size()>0?ors.get(0):null;*/
		//System.out.println("------------"+ors.get(0));
		//AfterSaleOrder aso = asor.findByImei(imei);
		
		//StockRemovalRecord srr = srrr.findOne(imei);
		List<Map<String, Object>> outInfo=new ArrayList<Map<String,Object>>();
		outInfo=b2bdao.findSzImei(imei);
		if(outInfo!=null&&outInfo.size()==0){
			outInfo=b2bdao.findGyImei(imei);
		}
		
		if(outInfo!=null&&outInfo.size()>0){
			Map<String, Object> m = outInfo.get(0);
			result.put("skucode", m.get("SKU_CODE").toString().trim());
			result.put("ddnum", m.get("ORDER_NUM").toString().trim());
			return result;
		}
		return null;
	}

	@Override
	public void save(AfterSaleForm entity) {
		List<AfterSaleType> types = getTypes(entity.getReceiveTime(), entity.getGoodsName(),entity.getSkuCode());
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
	public List<AfterSaleType> getTypes(Date receiveTime, String goodsName,String skuCode) {
		// TODO 计算可享受服务 不准确！！！
		List<AfterSaleType> resultList=new ArrayList<AfterSaleType>();
		
		if(null==receiveTime){//没有签收那么可以享受所有服务
			//没有签收时间和签收时间为空。可以享受所有服务
			if(null!=goodsName&&!"".equals(goodsName)){
				resultList.add(AfterSaleType.KXS);
				//编码中包含一个字母的不能退换货(有字母的是定制机)
//				int count=getLetter(skuCode);
//				if(count != 1){
					resultList.add(AfterSaleType.THH30);
//				}
			}
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
//				int count=getLetter(skuCode);
//				if(count != 1){
					resultList.add(AfterSaleType.THH30);
//				}
			}
			//if(ApplicationUtil.getWx()>=Poor){//维修
				resultList.add(AfterSaleType.WX);
			//}
			if(ApplicationUtil.getDmdhx100()>=Poor && goodsName.indexOf("多美达")>-1){
				resultList.add(AfterSaleType.DMDHX100);
			}
		}
		return resultList;
	}
	@Override
	public AfterSaleForm findByTradingId(String tradingId) {
		return asfRepository.findByTradingId(tradingId);
	}
	
	public int getLetter(String skuCode){
		int nLetter=0;
		for(int i=0;i<skuCode.length();i++){
			if(Character.isLetter(skuCode.charAt(i))){
				nLetter++;
			}
		}
		System.out.println("字母个数:"+nLetter);
		return nLetter;
	}
}

