package com.lzj.bean;

import java.util.Date;

public class Blotter {

	private int id;
	private int userId;
	private float szzs;
	private float balance;
	private float balanceYy;
	private Date createDate;
	
	public float getSzzs() {
		return szzs;
	}
	public void setSzzs(float szzs) {
		this.szzs = szzs;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getBalanceYy() {
		return balanceYy;
	}
	public void setBalanceYy(float balanceYy) {
		this.balanceYy = balanceYy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
