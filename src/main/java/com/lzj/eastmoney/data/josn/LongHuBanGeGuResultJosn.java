package com.lzj.eastmoney.data.josn;

import java.util.List;

public class LongHuBanGeGuResultJosn {
	
	private boolean success;
	private int pages;
	private List<LongHuBanGeGuJosn> data;
	private String url;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public List<LongHuBanGeGuJosn> getData() {
		return data;
	}
	public void setData(List<LongHuBanGeGuJosn> data) {
		this.data = data;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "LongHuBanGeGuResultJosn [success=" + success + ", pages=" + pages + ", data=" + data + ", url=" + url
				+ "]";
	}
	
}
