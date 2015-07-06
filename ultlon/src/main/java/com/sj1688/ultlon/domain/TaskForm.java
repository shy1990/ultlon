package com.sj1688.ultlon.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "tb_task_form")
public class TaskForm extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch=FetchType.EAGER)
	private AfterSaleForm afterSaleForm;//售后服务
	
	@Enumerated(EnumType.STRING)
	private FormAuditStatus status=FormAuditStatus.NOPROCESS;
	private String area;
	private String remark;
    private String marketbale_price;
    private String deal_price;
    private String pay_time;
    private String ship_name;
    private String address;
    private String goods_id;
	public TaskForm() {
		super();
	}

	public TaskForm(AfterSaleForm afterSaleForm,String area) {
		super();
		this.afterSaleForm = afterSaleForm;
		this.area=area;
	}



	public AfterSaleForm getAfterSaleForm() {
		return afterSaleForm;
	}

	public void setAfterForm(AfterSaleForm afterForm) {
		this.afterSaleForm = afterForm;
	}

	public FormAuditStatus getStatus() {
		return status;
	}

	public void setStatus(FormAuditStatus status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMarketbale_price() {
		return marketbale_price;
	}

	public void setMarketbale_price(String marketbale_price) {
		this.marketbale_price = marketbale_price;
	}

	public String getDeal_price() {
		return deal_price;
	}

	public void setDeal_price(String deal_price) {
		this.deal_price = deal_price;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
