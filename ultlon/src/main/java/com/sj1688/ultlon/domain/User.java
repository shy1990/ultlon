package com.sj1688.ultlon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
@Entity
@Table(name="tb_user")
public class User extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String username;
	private String password;
	private String regions;//所在区域
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public User() {
		super();
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

}
