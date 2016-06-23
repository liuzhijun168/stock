package com.lzj;

import java.util.List;

public class ChiGuDetail {

	private float yue;
	private float keyong;
	private float cankaoshizhi;
	private float zichang;
	private float yinkui;
	private float fuzhai;
    private List<ChiGu> chiguList;
	public float getYue() {
		return yue;
	}
	public void setYue(float yue) {
		this.yue = yue;
	}
	public float getKeyong() {
		return keyong;
	}
	public void setKeyong(float keyong) {
		this.keyong = keyong;
	}
	public float getCankaoshizhi() {
		return cankaoshizhi;
	}
	public void setCankaoshizhi(float cankaoshizhi) {
		this.cankaoshizhi = cankaoshizhi;
	}
	public float getZichang() {
		return zichang;
	}
	public void setZichang(float zichang) {
		this.zichang = zichang;
	}
	public float getYinkui() {
		return yinkui;
	}
	public void setYinkui(float yinkui) {
		this.yinkui = yinkui;
	}
	public List<ChiGu> getChiguList() {
		return chiguList;
	}
	public void setChiguList(List<ChiGu> chiguList) {
		this.chiguList = chiguList;
	}
	public float getFuzhai() {
		return fuzhai;
	}
	public void setFuzhai(float fuzhai) {
		this.fuzhai = fuzhai;
	}
	@Override
	public String toString() {
		return "ChiGuDetail [yue=" + yue + ", keyong=" + keyong
				+ ", cankaoshizhi=" + cankaoshizhi + ", zichang=" + zichang
				+ ", yinkui=" + yinkui + ", fuzhai=" + fuzhai + ", chiguList="
				+ chiguList + "]";
	}
	
}
