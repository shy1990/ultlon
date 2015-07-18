package com.sj1688.ultlon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractAuditable;

import com.google.common.base.Splitter;
@Entity
@Table(name="tb_user")
public class User extends AbstractAuditable<User,Long>{

	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String username;
	private String password;
	private String mobile;
	private String regions;//所在区域
	private String roler;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegions() {
		return regions;
	}
	public void setRegions(String regions) {
		this.regions = regions;
	}
	
	public String getRoler() {
		return roler;
	}


	public void setRoler(String roler) {
		this.roler = roler;
	}


	@Transient
	public List<String> getRegionList(){
		List<String> result = Splitter.on(",").trimResults().splitToList(this.regions);
		return result;
	}
}
