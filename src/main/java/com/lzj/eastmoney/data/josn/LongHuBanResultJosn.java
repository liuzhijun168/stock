package com.lzj.eastmoney.data.josn;

import java.util.List;

public class LongHuBanResultJosn {
	
	private boolean success;
	private int pages;
	private List<LongHuBanJosn> data;
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
	public List<LongHuBanJosn> getData() {
		return data;
	}
	public void setData(List<LongHuBanJosn> data) {
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
		return "LongHuBanResultJosn [success=" + success + ", pages=" + pages + ", data=" + data + ", url=" + url + "]";
	}
}
