package com.lzj;

public class GuPiaoData {
	private String code;
	private String name;
	private float zuigao;
	private float zhangdiefu;
	private float zuidi;
	private float kaipan;
	private float zuoshou;
	private float shoupan;
	private String suoshuhangye;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getZhangdiefu() {
		return zhangdiefu;
	}
	public void setZhangdiefu(float zhangdiefu) {
		this.zhangdiefu = zhangdiefu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getZuigao() {
		return zuigao;
	}
	public void setZuigao(float zuigao) {
		this.zuigao = zuigao;
	}
	public float getZuidi() {
		return zuidi;
	}
	public void setZuidi(float zuidi) {
		this.zuidi = zuidi;
	}
	public float getKaipan() {
		return kaipan;
	}
	public void setKaipan(float kaipan) {
		this.kaipan = kaipan;
	}
	
	public float getZuoshou() {
		return zuoshou;
	}
	public void setZuoshou(float zuoshou) {
		this.zuoshou = zuoshou;
	}
	public float getShoupan() {
		return shoupan;
	}
	public void setShoupan(float shoupan) {
		this.shoupan = shoupan;
	}
	public String getSuoshuhangye() {
		return suoshuhangye;
	}
	public void setSuoshuhangye(String suoshuhangye) {
		this.suoshuhangye = suoshuhangye;
	}
	@Override
	public String toString() {
		return "GuPiaoData [code=" + code + ", name=" + name + ", zuigao="
				+ zuigao + ", zuidi=" + zuidi + ", kaipan=" + kaipan
				+ ", zuoshou=" + zuoshou + ", shoupan=" + shoupan + "]";
	}
	
}
