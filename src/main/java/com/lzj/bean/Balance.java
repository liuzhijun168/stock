package com.lzj.bean;

import java.util.Date;

public class Balance {
	
	private int id;
	private float balance;
	private String remark;
	private Date createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "Balance [id=" + id + ", balance=" + balance + ", remark=" + remark + ", createDate=" + createDate + "]";
	}
	
	
	
}
