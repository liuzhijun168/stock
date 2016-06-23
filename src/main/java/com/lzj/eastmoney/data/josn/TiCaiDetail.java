package com.lzj.eastmoney.data.josn;

import java.util.Date;

public class TiCaiDetail {
	
	private int id;
	private String code;
	private int orderBy;
	private String content;
	private Date createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "TiCaiDetail [id=" + id + ", code=" + code + ", orderBy=" + orderBy + ", content=" + content
				+ ", createDate=" + createDate + "]";
	}
	
	
	
}
