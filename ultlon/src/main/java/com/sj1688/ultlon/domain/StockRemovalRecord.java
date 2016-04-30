package com.sj1688.ultlon.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
/**
 * 出库记录，停用神舟每天向这个表导入数据然后从这个表根据串码（id）查询相关订单和单品记录
 * @author xq
 *
 */

@Entity
@Table(name="tb_stock_removal_record")
public class StockRemovalRecord{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String orderNum;
	private String skuCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
}
